package com.bibliotheque.webapp.controller;

import com.bibliotheque.webapp.business.ApiConfigModel;
import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Livre;
import io.swagger.client.model.Reservation;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class LivreController {
    private LivreApi serviceLivre;
    private UtilisateurApi serviceUtilisateur;
    private ReservationApi serviceReservation;

    public LivreController(LivreApi serviceLivre, UtilisateurApi serviceUtilisateur, ReservationApi serviceReservation) {
        this.serviceLivre = serviceLivre;
        this.serviceUtilisateur = serviceUtilisateur;
        this.serviceReservation = serviceReservation;
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
                List<Reservation> reservationsAttente = serviceReservation.getReservationsOfaBookWaiting(id).execute().body();
                DateTime dateRetour = null;

                int livresDispo;
                if (reservations != null) {
                    livresDispo = livre.getQuantite() - reservations.size();
                } else {
                    livresDispo = livre.getQuantite();
                }
                if (livresDispo < 0)
                    livresDispo = 0;

                if (reservations != null && livresDispo == 0)
                    model.addAttribute("dateRetour", ApiConfigModel.findSmallestDate(reservations));

                model.addAttribute("reservationsAttente", (reservationsAttente != null) ? reservationsAttente.size() : 0);
                model.addAttribute("livresDispo", livresDispo);
                model.addAttribute("livre", livre);
                return "livre";
            }
            return "error";
        } catch (Exception e) {
            return "error";
        }
    }
}
