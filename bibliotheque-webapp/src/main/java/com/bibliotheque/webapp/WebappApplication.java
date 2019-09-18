package com.bibliotheque.webapp;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Livre;
import io.swagger.client.model.Utilisateur;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Base64;

@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) throws IOException {

/*        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        LivreApi serviceLivre = retrofit.create(LivreApi.class);
        ReservationApi serviceReservation = retrofit.create(ReservationApi.class);
        UtilisateurApi serviceUtilisateur = retrofit.create(UtilisateurApi.class);
        String base = "fjoseph24@orange.fr:azerty123";
*/
        SpringApplication.run(WebappApplication.class, args);
    }
}
