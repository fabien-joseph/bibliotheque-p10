package com.bibliotheque.batch.scheduler;

import com.bibliotheque.batch.consumer.ExpirationMail;
import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Reservation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlerteDisponibiliteScheduled {
    private LivreApi serviceLivre;
    private ReservationApi serviceReservation;
    private UtilisateurApi serviceUtilisateur;
    private ExpirationMail expirationMail = new ExpirationMail();

    public AlerteDisponibiliteScheduled(LivreApi serviceLivre, ReservationApi serviceReservation, UtilisateurApi serviceUtilisateur) {
        this.serviceLivre = serviceLivre;
        this.serviceReservation = serviceReservation;
        this.serviceUtilisateur = serviceUtilisateur;
    }

    @Scheduled(cron = "0 6 * * * *")
    public void checkReservationDisponible () {
    }

}
