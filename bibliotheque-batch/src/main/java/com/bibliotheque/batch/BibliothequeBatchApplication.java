package com.bibliotheque.batch;

		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BibliothequeBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliothequeBatchApplication.class, args);
	}

}
