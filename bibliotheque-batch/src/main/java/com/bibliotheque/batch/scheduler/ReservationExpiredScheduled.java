package com.bibliotheque.batch.scheduler;

import com.bibliotheque.batch.consumer.ExpirationMail;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationExpiredScheduled {
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    private LivreApi serviceLivre = retrofit.create(LivreApi.class);
    private UtilisateurApi serviceUtilisateur = retrofit.create(UtilisateurApi.class);
    private ReservationApi serviceReservation = retrofit.create(ReservationApi.class);

    private ExpirationMail expirationMail = new ExpirationMail();

    @Autowired
    public ReservationExpiredScheduled(ExpirationMail expirationMail) {
    }

    @Scheduled(cron = "0 6 * * * *")
    public void checkingReservationExpired() throws IOException, MailjetSocketTimeoutException, MailjetException {
        List<Reservation> reservationsApi = serviceReservation.expiredReservation().execute().body();
        if (reservationsApi != null) {
            List<com.bibliotheque.batch.model.Reservation> reservationsExpired =
                    convertListReservationApiToListReservation(reservationsApi);

            if (reservationsExpired.size() > 0) {
                for (com.bibliotheque.batch.model.Reservation reservation : reservationsExpired) {
                    expirationMail.mailReservationExpired(reservation);
                }
            }
        }
    }

    private com.bibliotheque.batch.model.Reservation convertReservationApiToReservation(Reservation reservationApi) throws IOException {
        com.bibliotheque.batch.model.Reservation reservation = new com.bibliotheque.batch.model.Reservation();

        System.out.println(reservationApi.getId() + ", " + reservationApi.getLivreId()  + ", " + reservationApi.getUtilisateurId()
                + ", " + reservationApi.getDateDebut()  + ", " + reservationApi.getDateFin());

        reservation.setId(reservationApi.getId());
        reservation.setDateDebut(reservationApi.getDateDebut());
        reservation.setDateFin(reservationApi.getDateFin());
        reservation.setRenouvelable(reservationApi.isRenouvelable());
        reservation.setRendu(reservationApi.isRendu());
        reservation.setLivre(serviceLivre.getLivreById(reservationApi.getLivreId()).execute().body());
        reservation.setUtilisateur(serviceUtilisateur.getUtilisateurById(reservationApi.getUtilisateurId()).execute().body());
        return reservation;
    }

    private List<com.bibliotheque.batch.model.Reservation> convertListReservationApiToListReservation(List<Reservation> reservationsApi) throws IOException {
        List<com.bibliotheque.batch.model.Reservation> reservations = new ArrayList<>();

        for (Reservation reservationApi : reservationsApi) {
            reservations.add(convertReservationApiToReservation(reservationApi));
        }
        return reservations;
    }
}
