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
    private LivreApi serviceLivre = retrofit.create(LivreApi.class);
    private UtilisateurApi serviceUtilisateur = retrofit.create(UtilisateurApi.class);
    private ReservationApi serviceReservation = retrofit.create(ReservationApi.class);

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
        List<Livre> livresReserves = new ArrayList<>();
        List<Livre> livresExpires = new ArrayList<>();
        if (session.getAttribute("utilisateurId") != null) {
            String idToConvert = String.valueOf(session.getAttribute("utilisateurId"));
            Long utilisateurId = Long.parseLong(idToConvert);
            List<Reservation> reservations = serviceReservation.findReservations(null, utilisateurId).execute().body();
            Utilisateur utilisateur = serviceUtilisateur.getUtilisateurById(utilisateurId).execute().body();
            if (reservations != null) {
                for (Reservation reservation : reservations)
                    if (reservation.getDateDebut().getMillis() <= new DateTime().getMillis() &&
                            reservation.getDateFin().getMillis() >= new DateTime().getMillis()) {
                        livresReserves.add(serviceLivre.getLivreById(reservation.getLivreId()).execute().body());
                    } else {
                        livresExpires.add(serviceLivre.getLivreById(reservation.getLivreId()).execute().body());
                    }
            }

            System.out.println("Livres actuels : " + livresReserves.size());
            System.out.println("Livres expirés : " + livresExpires.size());

            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("livresReserves", livresReserves);
            model.addAttribute("livresExpires", livresExpires);
            model.addAttribute("reservations", reservations);
            return "profile";
        }
        return "redirect:/connexion";
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

    @PostMapping("/connexionPost")
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
        System.out.println("Fail");
        return "redirect:/connexion";
    }

    @GetMapping("/deconnexion")
    public String deconnexion(HttpSession session) {
        session.removeAttribute("utilisateurId");
        return "redirect:/";
    }
}
