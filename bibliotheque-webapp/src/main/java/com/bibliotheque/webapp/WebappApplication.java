package com.bibliotheque.webapp;

import io.swagger.client.api.ReservationApi;
import io.swagger.client.api.UtilisateurApi;
import io.swagger.client.model.Reservation;
import io.swagger.client.model.Utilisateur;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class WebappApplication {

	public static void main(String[] args) throws IOException {
/*		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090/fab24/bibliotheque-livres/1.0.0/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		ReservationApi service = retrofit.create(ReservationApi.class);

		System.out.println(new Date());

		List<Reservation> reservations = service.findReservations(1L, null).execute().body();
		if (reservations != null) {
			System.out.println("Size : " + reservations.size());
		} else {
			System.out.println("Pas trouvé");
		}*/
		SpringApplication.run(WebappApplication.class, args);
	}
}
