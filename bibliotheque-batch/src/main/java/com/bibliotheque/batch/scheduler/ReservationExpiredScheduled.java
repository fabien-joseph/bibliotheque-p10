package com.bibliotheque.batch.scheduler;

import com.bibliotheque.batch.business.ApiConfigModel;
import com.bibliotheque.batch.consumer.ExpirationMail;
import com.bibliotheque.batch.consumer.ExpirationSoonMail;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
public class ReservationExpiredScheduled {
    private LivreApi serviceLivre;
    private UtilisateurApi serviceUtilisateur;
    private ReservationApi serviceReservation;
    private final ApiConfigModel apiConfigModel;
    private ExpirationMail expirationMail = new ExpirationMail();
    private ExpirationSoonMail expirationSoonMail = new ExpirationSoonMail();

    public ReservationExpiredScheduled(LivreApi serviceLivre, UtilisateurApi serviceUtilisateur, ReservationApi serviceReservation, ApiConfigModel apiConfigModel) {
        this.serviceLivre = serviceLivre;
        this.serviceUtilisateur = serviceUtilisateur;
        this.serviceReservation = serviceReservation;
        this.apiConfigModel = apiConfigModel;
    }

    @Scheduled(cron = "0 6 * * * *")
    public void checkingReservationExpired() throws IOException, MailjetSocketTimeoutException, MailjetException {
        List<Reservation> reservationsApi = serviceReservation.expiredReservation(null).execute().body();
        if (reservationsApi == null) {
            return;
        }
        List<com.bibliotheque.batch.model.Reservation> reservationsExpired =
                apiConfigModel.convertListReservationApiToListReservation(reservationsApi);

        if (reservationsExpired.size() > 0) {
            for (com.bibliotheque.batch.model.Reservation reservation : reservationsExpired) {
                expirationMail.mailReservationExpired(reservation);
            }
        }
    }

    @Scheduled (cron = "* * * * * *")
    public void checkReservationExpiredSoon() throws IOException, MailjetSocketTimeoutException, MailjetException {
        List<Reservation> reservationsApi = serviceReservation.expiredReservation(5).execute().body();

        System.out.println("Size = " + (reservationsApi != null ? reservationsApi.size() : 0));
/*        if (reservationsApi == null) {
            return;
        }

        List<com.bibliotheque.batch.model.Reservation> reservationsWillExpire =
                apiConfigModel.convertListReservationApiToListReservation(reservationsApi);

        if (reservationsWillExpire.size() > 0) {
            expirationSoonMail.mailReservationExpiredSoon(reservationsWillExpire);
        }*/
    }
}
