package com.bibliotheque.webapp.configuration;

import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class ApplicationConfiguration {
    @Value("${api.url}")
    private String apiUrl;

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public LivreApi livreApi(Retrofit retrofit) {
        return retrofit.create(LivreApi.class);
    }

    @Bean
    public UtilisateurApi utilisateurApi(Retrofit retrofit) {
        return retrofit.create(UtilisateurApi.class);
    }

    @Bean
    public ReservationApi reservationApi(Retrofit retrofit) {
        return retrofit.create(ReservationApi.class);
    }
}
