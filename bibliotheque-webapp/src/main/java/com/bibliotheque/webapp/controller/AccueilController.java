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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccueilController {
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    private LivreApi serviceLivre;
    private UtilisateurApi serviceUtilisateur = retrofit.create(UtilisateurApi.class);
    private ReservationApi serviceReservation = retrofit.create(ReservationApi.class);

    public AccueilController(LivreApi serviceLivre) {
        this.serviceLivre = serviceLivre;
    }

    @GetMapping("/")
    public String accueil(Model model, HttpSession session) {
        return "accueil";
    }

    @GetMapping("/livres")
    public String livres(Model model,
                         @RequestParam(value = "search", required = false, defaultValue = "") String search)
            throws IOException {
        model.addAttribute("livres", serviceLivre.findLivres(search).execute().body());
        return "livres";
    }

    @GetMapping("/livres/{id}")
    public String livre(Model model, @PathVariable Long id) throws IOException {
        Livre livre = serviceLivre.getLivreById(id).execute().body();
        model.addAttribute("livre", livre);
        return "livre";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) throws IOException {
        if (session.getAttribute("utilisateurId") != null) {
            String idToConvert = String.valueOf(session.getAttribute("utilisateurId"));
            Long utilisateurId = Long.parseLong(idToConvert);
            List<Reservation> reservations = serviceReservation.findReservations(null, utilisateurId).execute().body();
            Utilisateur utilisateur = serviceUtilisateur.getUtilisateurById(utilisateurId).execute().body();

            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("today", new DateTime().getMillis());
            model.addAttribute("reservations", convertListReservationApiToListReservation(reservations));
            return "profile";
        }
        return "redirect:/connexion";
    }

    @GetMapping("/renouveller/{reservationId}")
    public String renouveller(@PathVariable Long reservationId, HttpSession session) throws IOException {
        if (session.getAttribute("utilisateurId") != null) {
        String idString = String.valueOf(session.getAttribute("utilisateurId"));
        Long idUser;
            idUser = Long.parseLong(idString);
            if (idUser.equals(serviceReservation.getReservationById(reservationId).execute().body().getUtilisateurId())) {
                serviceReservation.renewReservation(reservationId).execute();
            }
        }
        return "redirect:/profile";
    }

    @GetMapping("/inscription")
    public String inscription() {
        return "inscription";
    }

    @GetMapping("/reservation")
    public String reservation() {
        return "reservation";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "connexion";
    }

    @PostMapping("/connexion")
    public String connexionPost(HttpSession session,
                                @RequestParam(value = "mail", required = false, defaultValue = "") String mail,
                                @RequestParam(value = "password", required = false, defaultValue = "") String password)
            throws IOException {
        if (serviceUtilisateur.connectUser(mail, password).execute().code() == 200) {
            Utilisateur utilisateur = serviceUtilisateur.findUtilisateursByMail(mail).execute().body();
            if (utilisateur != null) {
                session.setAttribute("utilisateurId", utilisateur.getId());
                return "redirect:/";
            }
        }
        return "redirect:/connexion";
    }

    @GetMapping("/deconnexion")
    public String deconnexion(HttpSession session) {
        session.removeAttribute("utilisateurId");
        return "redirect:/";
    }

    private com.bibliotheque.webapp.model.Reservation convertReservationApiToReservation(Reservation reservationApi) throws IOException {
        com.bibliotheque.webapp.model.Reservation reservation = new com.bibliotheque.webapp.model.Reservation();
        System.out.println(reservationApi.isRendu());
        reservation.setId(reservationApi.getId());
        reservation.setDateDebut(new DateTime(reservationApi.getDateDebut()));
        reservation.setDateFin(new DateTime(reservationApi.getDateFin()));
        reservation.setRendu(reservationApi.isRendu());
        reservation.setRenouvelable(reservationApi.isRenouvelable());
        reservation.setUtilisateur(serviceUtilisateur.getUtilisateurById(reservationApi.getUtilisateurId()).execute().body());
        reservation.setLivre(serviceLivre.getLivreById(reservationApi.getLivreId()).execute().body());
        System.out.println(reservation.isRendu());

        return reservation;
    }

    private List<com.bibliotheque.webapp.model.Reservation> convertListReservationApiToListReservation(List<Reservation> reservationsApi) throws IOException {
        List<com.bibliotheque.webapp.model.Reservation> reservations = new ArrayList<>();

        for (Reservation reservation : reservationsApi) {
            reservations.add(convertReservationApiToReservation(reservation));
        }
        return reservations;
    }
}
