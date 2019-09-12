package com.bibliotheque.webapp.configuration;

import io.swagger.client.api.LivreApi;
import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

@Configuration
public class ApplicationConfiguration {
    private OkHttpClient client() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Basic Zmpvc2VwaDI0QG9yYW5nZS5mcjphemVydHkxMjM=")
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
    }

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .client(client())
                .baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
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
