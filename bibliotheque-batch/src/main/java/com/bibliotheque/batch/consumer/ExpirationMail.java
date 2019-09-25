package com.bibliotheque.batch.consumer;

import com.bibliotheque.batch.model.Reservation;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ExpirationMail extends Mail {
    public void mailReservationExpired(Reservation reservation)
            throws MailjetSocketTimeoutException, MailjetException {
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", System.getenv("MJ_MAIL_FROM"))
                                        .put("Name", System.getenv("MJ_NAME_FROM")))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", reservation.getUtilisateur().getMail())
                                                .put("Name", reservation.getUtilisateur().getNom() + " " + reservation.getUtilisateur().getPrenom())))
                                .put(Emailv31.Message.SUBJECT, "Bibliotheque - Réservation expirée.")
                                .put(Emailv31.Message.TEXTPART, "Réservation n° " + reservation.getId() +
                                        " \"" + reservation.getLivre().getNom() + "\"" + " expirée." )
                                .put(Emailv31.Message.HTMLPART,
                "<h3>Veuillez rapporter le livre \"" + reservation.getLivre().getNom() + "\" à la bibliothèque dès que possible. Si vous n'avez pas la possibilité de le rendre avant une semaine veuillez nous prévenir en nous appelant, <a href=\"http://localhost:8080/contact\">cliquez ici</a> pour trouver notre numéro.</h3>" +
                        "<br>Si vous souhaitez savoir si vous pouvez renouveler votre réservation rendez-vous sur votre profil <a href=\"http://localhost:8080/connexion\">cliquez ici</a>.")));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
