package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.ReservationRepository;
import com.bibliotheque.api.model.Livre;
import com.bibliotheque.api.model.Reservation;
import com.bibliotheque.api.model.Utilisateur;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationManagement extends JpaCrudManager<Reservation, ReservationRepository> {
    @Autowired
    LivreManagement livreManagement;

    public ReservationManagement(ReservationRepository repository) {
        super(repository);
    }

    public void renew(Long id) {
        Optional<Reservation> reservation = repository.findById(id);
        if (reservation.isPresent()) {
            reservation.get().setDateDebut(new DateTime());
            reservation.get().setRenouvelable(false);
            repository.save(reservation.get());
        }
    }

    public List<Reservation> findActualReservations(Livre livre, Utilisateur utilisateur) {
        return repository.findActualReservationsWithLivre(livre, utilisateur, new DateTime());
    }

    public List<Reservation> getReservationsOfaBookInProgress(Livre livre) {
        return repository.getReservationsOfaBookInProgress(livre);
    }

    public List<Reservation> getReservationsOfaBookWaiting(Livre livre) {
        return repository.getReservationWaitingOfaBook(livre);
    }

    public Integer getPlaceOfaReservationWaiting(Reservation reservation) {
        List<Reservation> reservations = repository.getReservationWaitingOfaBook(reservation.getLivre());
        Integer place = null;
        for (Reservation reserv :
                reservations) {
            if (reserv.getId().equals(reservation.getId())) {
                place = reservations.indexOf(reserv) + 1;
                break;
            }
        }
        return place;
    }

    public List<Reservation> getReservationsToAlert() {
        List<Reservation> allReservationsWaiting = repository.findReservationsByAttenteTrue();
        List<Long> livresId = new ArrayList<>();
        for (Reservation reservation : allReservationsWaiting) {
            if (!isAlreadyPresent(livresId, reservation.getLivre().getId()))
                livresId.add(reservation.getLivre().getId());
        }

        List<Reservation> reservations = new ArrayList<>();
        for (Long aLong : livresId) {
            reservations.add
                    (repository.findFirstByLivreAndAttenteTrueOrderByDateCreationAsc
                            (livreManagement.findById(aLong).get()));
        }
        return reservations;
    }

    private boolean isAlreadyPresent(List<Long> livresId, Long id){
        for (Long aLong : livresId) {
            if (aLong.equals(id))
                return true;
        }
        return false;
    }

    public List<Reservation> findReservationsByAlertedTrue () {
        return repository.findReservationsByAlertedTrue();
    }

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Reservation reservation) {
        if (!canBeSaved(reservation))
            return;
        repository.save(reservation);
    }


    public void deleteList(List<Reservation> reservationsToDelete) {
        for (Reservation reservation : reservationsToDelete) {
            repository.delete(reservation);
        }
    }

    /*
    * Méthode pour savoir si une réservation peut être enregistrées. Conditions :
    * - Qu'il y a moins de réservation que 2x la quantité max de livre disponibles
    * - Si la réservation existe déjà on autorise le save (vu que c'est un update)
     */
    public boolean canBeSaved(Reservation reservation) {
        List<Reservation> reservations = repository.getReservationWaitingOfaBook(reservation.getLivre());
        Optional<Reservation> reservationExisting = repository.findById(reservation.getId());

        if (reservations != null && !reservationExisting.isPresent()) {
            if (reservations.size() >= reservation.getLivre().getQuantite() * 2) {
                return false;
            }
            for (Reservation res : reservations) {
                if (reservation.getLivre() == res.getLivre() && reservation.getUtilisateur() == res.getUtilisateur()) {
                    return false;
                }
            }
        }

        return true;
    }
}
