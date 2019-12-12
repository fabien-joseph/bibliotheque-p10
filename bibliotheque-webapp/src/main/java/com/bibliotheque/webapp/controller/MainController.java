package com.bibliotheque.webapp.controller;

import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Livre;
import io.swagger.client.model.Reservation;
import io.swagger.client.model.Utilisateur;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class MainController {
    private LivreApi serviceLivre;
    private UtilisateurApi serviceUtilisateur;
    private ReservationApi serviceReservation;

    public MainController(LivreApi serviceLivre, UtilisateurApi serviceUtilisateur, ReservationApi serviceReservation) {
        this.serviceLivre = serviceLivre;
        this.serviceUtilisateur = serviceUtilisateur;
        this.serviceReservation = serviceReservation;
    }

    @GetMapping("/")
    public String accueil(Model model, HttpSession session) {
        return "accueil";
    }

    @GetMapping("/livres")
    public String livres(Model model,
                         @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        try {
            model.addAttribute("livres", serviceLivre.findLivres(search).execute().body());
            return "livres";
        } catch (Exception e) {
            return "failConnexion";
        }
    }

    @GetMapping("/livres/{id}")
    public String livre(Model model, @PathVariable Long id) {
        try {
            Livre livre = serviceLivre.getLivreById(id).execute().body();
            if (livre != null) {
                List<Reservation> reservations = serviceReservation.getReservationsOfaBookInProgress(id).execute().body();
                int reservationsSize = 0;
                if (reservations != null)
                    reservationsSize = livre.getQuantite() - reservations.size();
                if (reservationsSize < 0)
                    reservationsSize = 0;
                model.addAttribute("livre", livre);
                model.addAttribute("reservationsSize", reservationsSize);
                return "livre";
            }
            return "error";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/profil")
    public String profil(Model model, HttpSession session) {
        try {
            if (session.getAttribute("mail") != null) {
                Utilisateur utilisateur = serviceUtilisateur.findUtilisateursByMail((String) session.getAttribute("mail")).execute().body();

                String base = session.getAttribute("mail") + ":" + session.getAttribute("password");

                assert utilisateur != null;
                List<Reservation> reservations = serviceReservation.findReservations(
                        "Basic " + Base64.getEncoder().encodeToString(base.getBytes())
                        , null, utilisateur.getId())
                        .execute()
                        .body();


                model.addAttribute("utilisateur", utilisateur);
                model.addAttribute("today", new DateTime().getMillis());
                model.addAttribute("today2", 18);
                model.addAttribute("reservations", convertListReservationApiToListReservation(reservations));
                model.addAttribute("number", 18);
                return "profil";
            }
            return "redirect:/connexion";
        } catch (Exception e) {
            return "failConnexion";
        }
    }

    @GetMapping("/renouveler/{reservationId}")
    public String renouveler(@PathVariable Long reservationId, HttpSession session) {
        try {
            if (session.getAttribute("mail") != null) {
                serviceReservation.renewReservation(reservationId, encodeHeaderAuthorization(session)).execute();
            }
            return "redirect:/profil";
        } catch (Exception e) {
            return "failConnexion";
        }
    }

    @GetMapping("/reservation/attente/{livreId}")
    public String reserverAttente(@PathVariable Long livreId, HttpSession session) {
        if (session.getAttribute("mail") == null)
            return "redirect:/connexion";
            try {
                Utilisateur utilisateur = serviceUtilisateur.findUtilisateursByMail(session.getAttribute("mail").toString()).execute().body();
            if (serviceLivre.getLivreById(livreId).execute().body() != null && utilisateur != null) {
                Calendar calendar = Calendar.getInstance();
                Reservation reservation = new Reservation();
                reservation.setLivreId(livreId);
                reservation.setUtilisateurId(utilisateur.getId());
                reservation.setDateDebut(calendar.getTimeInMillis());
                calendar.add(Calendar.DATE, 30);
                reservation.setDateFin(calendar.getTimeInMillis());
                reservation.setAttente(true);
                reservation.setRendu(false);
                 reservation.setRenouvelable(true);
                System.out.println("Ça part");
                serviceReservation.addReservation(reservation, encodeHeaderAuthorization(session)).execute();
                return "redirect:/profil";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/accueil";
    }


    @GetMapping("/inscription")
    public String inscription() {
        return "inscription";
    }

    @GetMapping("/reservation/{livreId}")
    public String reservation(Model model, @PathVariable Long livreId) {
        int livresDispo = 0;
        try {
            Livre livre = serviceLivre.getLivreById(livreId).execute().body();
            if (livre == null)
                return "error";
            List<Reservation> reservations = serviceReservation.getReservationsOfaBookInProgress(livre.getId()).execute().body();
            if (reservations == null) {
                livresDispo = livre.getQuantite();
            } else {
                livresDispo = livre.getQuantite() - reservations.size();
            }
            if (livresDispo > 0)
                livresDispo = 0;
            model.addAttribute("livresDispo", livresDispo);
            model.addAttribute("livre", livre);
            return "reservation";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/connexion")
    public String connexion(HttpSession session) {
        if (session.getAttribute("mail") != null)
            return "redirect:/profil";
        return "connexion";
    }

    @PostMapping("/connexion")
    public String connexionPost(HttpSession session,
                                @RequestParam(value = "mail", required = false, defaultValue = "") String mail,
                                @RequestParam(value = "password", required = false, defaultValue = "") String password) {
        try {
            if (serviceUtilisateur.connectUser(mail, password).execute().code() == 200) {
                session.setAttribute("mail", mail);
                session.setAttribute("password", password);
                return "redirect:/";
            }
            return "redirect:/connexion";
        } catch (Exception e) {
            return "failConnexion";
        }
    }

    @GetMapping("/deconnexion")
    public String deconnexion(HttpSession session) {
        session.removeAttribute("mail");
        session.removeAttribute("password");
        return "redirect:/";
    }

    private com.bibliotheque.webapp.model.Reservation convertReservationApiToReservation(Reservation reservationApi) throws IOException {
        com.bibliotheque.webapp.model.Reservation reservation = new com.bibliotheque.webapp.model.Reservation();
        reservation.setId(reservationApi.getId());
        reservation.setDateDebut(new DateTime(reservationApi.getDateDebut()));
        reservation.setDateFin(new DateTime(reservationApi.getDateFin()));
        reservation.setRendu(reservationApi.isRendu());
        reservation.setRenouvelable(reservationApi.isRenouvelable());
        reservation.setUtilisateur(serviceUtilisateur.getUtilisateurById(reservationApi.getUtilisateurId()).execute().body());
        reservation.setLivre(serviceLivre.getLivreById(reservationApi.getLivreId()).execute().body());

        return reservation;
    }

    private List<com.bibliotheque.webapp.model.Reservation> convertListReservationApiToListReservation(List<Reservation> reservationsApi) throws IOException {
        if (reservationsApi != null) {
            List<com.bibliotheque.webapp.model.Reservation> reservations = new ArrayList<>();
            for (Reservation reservation : reservationsApi) {
                reservations.add(convertReservationApiToReservation(reservation));
            }
            return reservations;
        }
        return null;
    }

    private String encodeHeaderAuthorization(HttpSession session) {
        String baseString = session.getAttribute("mail") + ":" + session.getAttribute("password");
        return "Basic " + Base64.getEncoder().encodeToString(baseString.getBytes());
    }
}
