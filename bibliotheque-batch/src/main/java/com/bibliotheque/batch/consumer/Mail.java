package com.bibliotheque.batch.consumer;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;

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
