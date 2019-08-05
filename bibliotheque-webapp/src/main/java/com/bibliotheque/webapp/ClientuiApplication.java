package com.bibliotheque.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ClientuiApplication {

	public static void main(String[] args) throws IOException {

		/*Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/fab24/bibliotheque-livres/1.0.0/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		UtilisateurApi service = retrofit.create(UtilisateurApi.class);

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom("Joseph");
		utilisateur.setPrenom("Fabien");
		utilisateur.setMotDePasse("azerty123");
		utilisateur.setNomDeCompte("KingFab");

		Call<Void> voidCall = service.addUtilisateur(utilisateur);

		voidCall.execute();
		ApiClient apiClient = new ApiClient();
		apiClient.createDefaultAdapter();
		UtilisateurApi service = apiClient.createService(UtilisateurApi.class);
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(0L);
		utilisateur.setNom("Joseph");
		service.addUtilisateur(utilisateur).execute();

		System.out.println(service.getUtilisateurById(0L).execute().body());

		 */
		SpringApplication.run(ClientuiApplication.class, args);
	}

}
