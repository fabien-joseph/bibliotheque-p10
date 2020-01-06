package com.bibliotheque.webapp.controller;

import com.bibliotheque.webapp.business.ApiConfigModel;
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
                serviceReservation.renewReservation(reservationId, ApiConfigModel.encodeHeaderAuthorization(session)).execute();
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
                serviceReservation.addReservation(reservation, ApiConfigModel.encodeHeaderAuthorization(session)).execute();
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
        serviceReservation.deleteReservation(reservationId, ApiConfigModel.encodeHeaderAuthorization(session)).execute();
        return "redirect:/profil";
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
                List<com.bibliotheque.webapp.model.Reservation> reservationsUser = ApiConfigModel.convertListReservationApiToListReservation(reservations);
                if (reservationsUser != null) {
                    ApiConfigModel.configReservationPlace(reservationsUser);
                }
                model.addAttribute("reservations", reservationsUser);
                return "profil";
            }
            return "redirect:/connexion";
        } catch (Exception e) {
            return "failConnexion";
        }
    }
}
