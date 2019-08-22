package com.bibliotheque.api.consumer;

import com.bibliotheque.api.model.Livre;
import com.bibliotheque.api.model.Reservation;
import com.bibliotheque.api.model.Utilisateur;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.livre = :livre AND r.dateDebut <= :date AND r.dateFin >= :date")
    List<Reservation> findActualReservationsWithLivre(@Param("livre") Livre livre, @Param("date") DateTime date);

    @Query("SELECT r FROM Reservation r WHERE r.utilisateur = :utilisateur  AND r.dateDebut <= :date AND r.dateFin >= :date")
    List<Reservation> findActualReservationsWithUtilisateur(@Param("utilisateur") Utilisateur utilisateur, @Param("date") DateTime date);

    @Query("SELECT r FROM Reservation r WHERE r.livre = :livre AND r.utilisateur = :utilisateur  AND r.dateDebut <= :date AND r.dateFin >= :date")
    List<Reservation> findActualReservationsWithLivreAndUtilisateur(@Param("livre") Livre livre,
                                                                    @Param("utilisateur") Utilisateur utilisateur, @Param("date") DateTime date);

}
