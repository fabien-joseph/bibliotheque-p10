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
public class AlerteDispoMail extends Mail{
    public void mailAlerteDispo(Reservation reservation)
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
                                .put(Emailv31.Message.SUBJECT, "Bibliotheque - Votre livre disponible.")
                                .put(Emailv31.Message.HTMLPART,
                                        "<h3>Le livre \"" + reservation.getLivre().getNom() + "\" est à présent disponible, vous pouvez dès à présent vous rendre à la bibliothèque pour le récupérer.")));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
