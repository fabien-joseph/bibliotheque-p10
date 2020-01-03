package com.bibliotheque.webapp.business;

import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Reservation;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class ApiConfigModel {
    private static LivreApi serviceLivre;
    private static UtilisateurApi serviceUtilisateur;
    private static  ReservationApi serviceReservation;

    public ApiConfigModel(LivreApi serviceLivre, UtilisateurApi serviceUtilisateur, ReservationApi serviceReservation) {
        ApiConfigModel.serviceLivre = serviceLivre;
        ApiConfigModel.serviceUtilisateur = serviceUtilisateur;
        ApiConfigModel.serviceReservation = serviceReservation;
    }

    public static com.bibliotheque.webapp.model.Reservation convertReservationApiToReservation(Reservation reservationApi) throws IOException {
        com.bibliotheque.webapp.model.Reservation reservation = new com.bibliotheque.webapp.model.Reservation();
        reservation.setId(reservationApi.getId());
        reservation.setDateCreation(new DateTime(reservationApi.getDateCreation()));
        reservation.setDateDebut((reservationApi.getDateDebut() != null) ? new DateTime(reservationApi.getDateDebut()) : null);
        reservation.setDateFin((reservationApi.getDateFin() != null) ? new DateTime(reservationApi.getDateFin()) : null);
        reservation.setDateRetourProche((reservationApi.getDateRetourProche() != null) ? new DateTime(reservationApi.getDateRetourProche()) : null);
        reservation.setRendu(reservationApi.isRendu());
        reservation.setRenouvelable(reservationApi.isRenouvelable());
        reservation.setAttente(reservationApi.isAttente());
        reservation.setUtilisateur(serviceUtilisateur.getUtilisateurById(reservationApi.getUtilisateurId()).execute().body());
        reservation.setLivre(serviceLivre.getLivreById(reservationApi.getLivreId()).execute().body());

        return reservation;
    }

    public static List<com.bibliotheque.webapp.model.Reservation> convertListReservationApiToListReservation(List<Reservation> reservationsApi) throws IOException {
        if (reservationsApi != null) {
            List<com.bibliotheque.webapp.model.Reservation> reservations = new ArrayList<>();
            for (Reservation reservation : reservationsApi) {
                reservations.add(convertReservationApiToReservation(reservation));
            }
            return reservations;
        }
        return null;
    }

    public static String encodeHeaderAuthorization(HttpSession session) {
        String baseString = session.getAttribute("mail") + ":" + session.getAttribute("password");
        return "Basic " + Base64.getEncoder().encodeToString(baseString.getBytes());
    }

    public static DateTime findSmallestDate (List<Reservation> reservations) {
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
}
