package com.bibliotheque.webapp.configuration;

import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Retrofit retrofit () {
        return new Retrofit.Builder().baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Bean
    public LivreApi livreApi(Retrofit retrofit) {
        return retrofit.create(LivreApi.class);
    }

    @Bean
    public UtilisateurApi utilisateurApi(Retrofit retrofit) {return retrofit.create(UtilisateurApi.class);}

    @Bean
    public ReservationApi reservationApi(Retrofit retrofit) {return retrofit.create(ReservationApi.class);}
}
