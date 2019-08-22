package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.ReservationRepository;
import com.bibliotheque.api.model.Livre;
import com.bibliotheque.api.model.Reservation;
import com.bibliotheque.api.model.Utilisateur;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationManagement extends JpaCrudManager<Reservation, ReservationRepository>{
    public ReservationManagement(ReservationRepository repository) {
        super(repository);
    }

    public void renew (Long id) {
        Optional<Reservation> reservation = repository.findById(id);
        if (reservation.isPresent()) {
            DateTime timeUpdate = reservation.get().getDateFin().plusDays(30);
            reservation.get().setDateFin(timeUpdate);
            repository.save(reservation.get());
        }
    }

    public List<Reservation> findActualReservations (Livre livre, Utilisateur utilisateur) {
        if (livre != null && utilisateur != null) {
            System.out.println("Entrée 1");
            return repository.findActualReservationsWithLivreAndUtilisateur(livre, utilisateur, new DateTime());
        } else if (utilisateur != null && livre == null) {
            System.out.println("Entrée 2");
            return repository.findActualReservationsWithUtilisateur(utilisateur, new DateTime());
        } else if (utilisateur == null && livre != null){
            System.out.println("Entrée 3");
            return repository.findActualReservationsWithLivre(livre, new DateTime());
        } else {
            System.out.println("Entrée 4");
            return null;
        }
    }
}
