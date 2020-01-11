package com.bibliotheque.batch.business;

import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Reservation;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class ApiConfigModel {
    private LivreApi serviceLivre;
    private UtilisateurApi serviceUtilisateur;
    private ReservationApi serviceReservation;

    public ApiConfigModel(LivreApi serviceLivre, UtilisateurApi serviceUtilisateur, ReservationApi serviceReservation) {
        this.serviceLivre = serviceLivre;
        this.serviceUtilisateur = serviceUtilisateur;
        this.serviceReservation = serviceReservation;
    }

    public com.bibliotheque.batch.model.Reservation convertReservationApiToReservation(Reservation reservationApi) throws IOException {
        com.bibliotheque.batch.model.Reservation reservation = new com.bibliotheque.batch.model.Reservation();
        reservation.setId(reservationApi.getId());
        reservation.setDateCreation(new DateTime(reservationApi.getDateCreation()));
        reservation.setDateDebut((reservationApi.getDateDebut() != null) ? new DateTime(reservationApi.getDateDebut()) : null);
        reservation.setDateFin((reservationApi.getDateFin() != null) ? new DateTime(reservationApi.getDateFin()) : null);
        reservation.setRendu(reservationApi.isRendu());
        reservation.setRenouvelable(reservationApi.isRenouvelable());
        reservation.setAttente(reservationApi.isAttente());
        reservation.setAlerted(reservationApi.isAlerted());
        reservation.setUtilisateur(serviceUtilisateur.getUtilisateurById(reservationApi.getUtilisateurId()).execute().body());
        reservation.setLivre(serviceLivre.getLivreById(reservationApi.getLivreId()).execute().body());

        return reservation;
    }


    public Reservation convertReservationToReservationApi (com.bibliotheque.batch.model.Reservation reservation)throws IOException{
        Reservation reservationApi = new Reservation();
        reservationApi.setId(reservation.getId());
        reservationApi.setDateCreation(reservation.getDateCreation().getMillis());
        reservationApi.setDateDebut(reservation.getDateDebut().getMillis());
        reservationApi.setDateFin(reservation.getDateFin().getMillis());
        reservationApi.setRendu(reservation.isRendu());
        reservationApi.setRenouvelable(reservation.isRenouvelable());
        reservationApi.setAttente(reservation.isAttente());
        reservationApi.setAlerted(reservation.isAlerted());
        reservationApi.setUtilisateurId(reservation.getUtilisateur().getId());
        reservationApi.setLivreId(reservation.getLivre().getId());

        return reservationApi;
    }

    public List<com.bibliotheque.batch.model.Reservation> convertListReservationApiToListReservation(List<Reservation> reservationsApi) throws IOException {
        if (reservationsApi != null) {
            List<com.bibliotheque.batch.model.Reservation> reservations = new ArrayList<>();
            for (Reservation reservation : reservationsApi) {
                reservations.add(convertReservationApiToReservation(reservation));
            }
            return reservations;
        }
        return null;
    }

    public String encodeHeaderAuthorization() {
        String baseString = System.getenv("API_MAIL") + ":" + System.getenv("API_PASSWORD");
        return "Basic " + Base64.getEncoder().encodeToString(baseString.getBytes());
    }
}
