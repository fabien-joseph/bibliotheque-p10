package com.bibliotheque.webapp;

import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Utilisateur;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) throws IOException {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UtilisateurApi service = retrofit.create(UtilisateurApi.class);
/*
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail("fab@gmail.com");
        utilisateur.setMotDePasse("azerty123");
        utilisateur.setNom("Joseph");
        utilisateur.setPrenom("Fabien");
        utilisateur.setIsBibliothecaire(false);
        service.addUtilisateur(utilisateur).execute();
 */
        //System.out.println(service.connectUser("fab@gmail.com", "aazerty123").execute().code());

        SpringApplication.run(WebappApplication.class, args);
    }
}
