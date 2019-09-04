package com.bibliotheque.batch.consumer;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    class Mail {
     MailjetRequest request;
     MailjetResponse response;
     MailjetClient client;

     Mail() {
        client = new MailjetClient(
                System.getenv("MJ_APIKEY_PUBLIC"),
                System.getenv("MJ_APIKEY_PRIVATE"),
                new ClientOptions("v3.1"));
    }
}
