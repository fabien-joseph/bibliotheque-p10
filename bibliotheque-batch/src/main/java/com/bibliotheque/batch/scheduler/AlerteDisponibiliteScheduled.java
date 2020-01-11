package com.bibliotheque.batch.scheduler;

import com.bibliotheque.batch.business.ApiConfigModel;
import com.bibliotheque.batch.consumer.AlerteDispoMail;
import com.bibliotheque.batch.consumer.ExpirationMail;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Reservation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
public class AlerteDisponibiliteScheduled {
    private LivreApi serviceLivre;
    private ReservationApi serviceReservation;
    private UtilisateurApi serviceUtilisateur;
    private final ApiConfigModel apiConfigModel;
    private AlerteDispoMail alerteDispoMail = new AlerteDispoMail();

    public AlerteDisponibiliteScheduled(LivreApi serviceLivre, ReservationApi serviceReservation, UtilisateurApi serviceUtilisateur, ApiConfigModel apiConfigModel) {
        this.serviceLivre = serviceLivre;
        this.serviceReservation = serviceReservation;
        this.serviceUtilisateur = serviceUtilisateur;
        this.apiConfigModel = apiConfigModel;
    }

    @Scheduled(cron = "0 6 */2 * * *")
    public void checkReservationDisponible () throws IOException, MailjetSocketTimeoutException, MailjetException {
        List<Reservation> reservationsApi = serviceReservation.getReservationsToAlertOfAReturn().execute().body();

        if (reservationsApi != null) {
            List<com.bibliotheque.batch.model.Reservation> reservationsAlert =
                    apiConfigModel.convertListReservationApiToListReservation(reservationsApi);

            if (reservationsAlert.size() > 0) {
                for (com.bibliotheque.batch.model.Reservation reservation :
                        reservationsAlert) {
                    alerteDispoMail.mailAlerteDispo(reservation);
                    reservation.setAlerted(true);
                    System.out.println(serviceReservation.addReservation(apiConfigModel.convertReservationToReservationApi(reservation),
                            apiConfigModel.encodeHeaderAuthorization()).execute().code());
                }
            }
        }
    }
}
