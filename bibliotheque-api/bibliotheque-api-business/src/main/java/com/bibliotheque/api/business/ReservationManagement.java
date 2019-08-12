package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.ReservationRepository;
import com.bibliotheque.api.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationManagement extends JpaCrudManager<Reservation, ReservationRepository>{
    public ReservationManagement(ReservationRepository repository) {
        super(repository);
    }
}
