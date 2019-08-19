package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.ReservationRepository;
import com.bibliotheque.api.model.Reservation;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

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
}
