package com.bibliotheque.webapp.controller;

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

import java.io.IOException;
import java.util.ArrayList;
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
                    model.addAttribute("dateRetour", findSmallestDate(reservations));

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

    private DateTime findSmallestDate (List<Reservation> reservations) {
        if (reservations == null)
            return null;

        Long dateLong = 0L;
        for (int i = 0; i < reservations.size(); i++) {
            if (i == 0) {
                dateLong = reservations.get(i).getDateFin();
            } else {
                if (reservations.get(i).getDateFin() < dateLong)
                    dateLong = reservations.get(i).getDateFin();
            }
        }
        return new DateTime(dateLong);
    }

    private com.bibliotheque.webapp.model.Reservation convertReservationApiToReservation(Reservation reservationApi) throws IOException {
        com.bibliotheque.webapp.model.Reservation reservation = new com.bibliotheque.webapp.model.Reservation();
        reservation.setId(reservationApi.getId());
        reservation.setDateCreation(new DateTime(reservationApi.getDateCreation()));
        reservation.setDateDebut((reservationApi.getDateDebut() != null) ? new DateTime(reservationApi.getDateDebut()) : null);
        reservation.setDateFin((reservationApi.getDateFin() != null) ? new DateTime(reservationApi.getDateFin()) : null);
        reservation.setRendu(reservationApi.isRendu());
        reservation.setRenouvelable(reservationApi.isRenouvelable());
        reservation.setAttente(reservationApi.isAttente());
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
}
