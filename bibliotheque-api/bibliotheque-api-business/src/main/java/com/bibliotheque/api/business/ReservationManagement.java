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
            reservation.get().setDateDebut(new DateTime());
            repository.save(reservation.get());
        }
    }

    public List<Reservation> findActualReservations (Livre livre, Utilisateur utilisateur) {
        return repository.findActualReservationsWithLivre(livre, utilisateur, new DateTime());
    }

    public List<Reservation> findAll() {
        return repository.findAll();
    }
}
