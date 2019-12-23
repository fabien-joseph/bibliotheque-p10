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

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

@Controller
public class ReservationController {

    private LivreApi serviceLivre;
    private UtilisateurApi serviceUtilisateur;
    private ReservationApi serviceReservation;

    public ReservationController(LivreApi serviceLivre, UtilisateurApi serviceUtilisateur, ReservationApi serviceReservation) {
        this.serviceLivre = serviceLivre;
        this.serviceUtilisateur = serviceUtilisateur;
        this.serviceReservation = serviceReservation;
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
                serviceReservation.addReservation(reservation, encodeHeaderAuthorization(session)).execute();
                return "redirect:/profil";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
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
            if (livresDispo < 0)
                livresDispo = 0;
            model.addAttribute("livresDispo", livresDispo);
            model.addAttribute("livre", livre);
            return "reservation";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/reservation/annuler/{reservationId}")
    public String reservationAnnulation(HttpSession session, @PathVariable Long reservationId) throws IOException {
        serviceReservation.deleteReservation(reservationId, encodeHeaderAuthorization(session)).execute();
        return "redirect:/profil";
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

    private String encodeHeaderAuthorization(HttpSession session) {
        String baseString = session.getAttribute("mail") + ":" + session.getAttribute("password");
        return "Basic " + Base64.getEncoder().encodeToString(baseString.getBytes());
    }
}
