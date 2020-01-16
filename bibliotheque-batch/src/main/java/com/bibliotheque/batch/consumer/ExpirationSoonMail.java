package com.bibliotheque.batch.consumer;

import com.bibliotheque.batch.model.Reservation;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import io.swagger.client.model.Utilisateur;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpirationSoonMail extends Mail {
    public void mailReservationExpiredSoon(List<Reservation> reservations)
            throws MailjetSocketTimeoutException, MailjetException {
        if (reservations.size() == 0)
            return;
        Utilisateur utilisateur = reservations.get(0).getUtilisateur();
        List<String> nomLivres = new ArrayList<>();

        for (Reservation reservation :
                reservations) {
            nomLivres.add(reservation.getLivre().getNom());
        }

        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", System.getenv("MJ_MAIL_FROM"))
                                        .put("Name", System.getenv("MJ_NAME_FROM")))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", utilisateur.getMail())
                                                .put("Name", utilisateur.getNom() + " " + utilisateur.getPrenom())))
                                .put(Emailv31.Message.SUBJECT, "Bibliotheque - Vos réservations expirent bientôt.")
                                .put(Emailv31.Message.TEXTPART, "Voici la liste de vos réservations qui vont bientôt expirées." )
                                .put(Emailv31.Message.HTMLPART, generateStringHtmlPart(reservations))));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }

    private String generateStringHtmlPart(List<Reservation> reservations) {
        StringBuilder stringReservationExpiredSoon = new StringBuilder("<ul>");
        for (Reservation reservation :
                reservations) {
            stringReservationExpiredSoon.append("<li>");
            stringReservationExpiredSoon.append(reservation.getLivre().getNom());
            stringReservationExpiredSoon.append(", à rendre le ");
            stringReservationExpiredSoon.append(reservation.getDateFin().dayOfMonth());
            stringReservationExpiredSoon.append("/");
            stringReservationExpiredSoon.append(reservation.getDateFin().monthOfYear());
            stringReservationExpiredSoon.append("</li>");

        }
        stringReservationExpiredSoon.append("</ul>");
        return stringReservationExpiredSoon.toString();
    }
}
