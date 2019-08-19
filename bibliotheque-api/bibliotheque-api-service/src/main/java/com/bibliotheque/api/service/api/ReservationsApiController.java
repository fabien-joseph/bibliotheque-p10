package com.bibliotheque.api.service.api;

import com.bibliotheque.api.business.LivreManagement;
import com.bibliotheque.api.business.ReservationManagement;
import com.bibliotheque.api.business.UtilisateurManagement;
import com.bibliotheque.api.service.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.threeten.bp.OffsetDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-11T23:01:18.100Z")

@Controller
public class ReservationsApiController implements ReservationsApi {

    private static final Logger log = LoggerFactory.getLogger(ReservationsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ReservationManagement reservationManagement;

    @Autowired
    private UtilisateurManagement utilisateurManagement;

    @Autowired
    private LivreManagement livreManagement;

    @org.springframework.beans.factory.annotation.Autowired
    public ReservationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addReservation(@ApiParam(value = "Un objet Reservation doit être envoyé pour être ajouté" ,required=true )  @Valid @RequestBody Reservation body) {
        String accept = request.getHeader("Accept");
        if (!reservationManagement.findById(body.getId()).isPresent()) {
            reservationManagement.save(convertReservationApiToReservation(body));
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    public ResponseEntity<Void> deleteReservation(@ApiParam(value = "ID du livre à supprimer",required=true) @PathVariable("reservationId") Long reservationId) {
        String accept = request.getHeader("Accept");
        if (reservationManagement.findById(reservationId).isPresent()) {
            reservationManagement.deleteById(reservationId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Reservation>> findReservations(@ApiParam(value = "Réservations faites sur l'id d'un livre",required=true) @PathVariable("livreId") Long livreId, @ApiParam(value = "Réservations faites par un utilisateur",required=true) @PathVariable("utilisateurId") Long utilisateurId, @ApiParam(value = "Réservations faites au de là d'une date",required=true) @PathVariable("dateDebut") OffsetDateTime dateDebut, @ApiParam(value = "Réservations faites au de là d'une date",required=true) @PathVariable("dateFin") OffsetDateTime dateFin) {
        String accept = request.getHeader("Accept");

        return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Reservation> getReservationById(@ApiParam(value = "ID of livre to return",required=true) @PathVariable("reservationId") Long reservationId) {
        Optional<com.bibliotheque.api.model.Reservation> reservation = reservationManagement.findById(reservationId);
        if (reservation.isPresent()) {
            return new ResponseEntity<Reservation>(convertReservationToReservationApi(reservation.get()), HttpStatus.OK);
        }
        return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> updateReservation(@ApiParam(value = "ID de la réservation qui doit être mise à jour",required=true) @PathVariable("reservationId") Long reservationId) {
        String accept = request.getHeader("Accept");
        if (reservationManagement.findById(reservationId).isPresent()) {
            reservationManagement.renew(reservationId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    Reservation convertReservationToReservationApi(com.bibliotheque.api.model.Reservation reservation) {
        Reservation reservationApi = new Reservation();

        reservationApi.setId(reservation.getId());
        reservationApi.setDateDebut(reservation.getDateDebut());
        reservationApi.setDateFin(reservation.getDateFin());
        reservationApi.setLivreId(reservation.getLivre().getId());
        reservationApi.setUtilisateurId(reservation.getUtilisateur().getId());
        return reservationApi;
    }

    List<Reservation> convertListReservationToListReservationApi(List<com.bibliotheque.api.model.Reservation> reservations) {
        List<Reservation> reservationsApi = new ArrayList<>();

        for (com.bibliotheque.api.model.Reservation reservation : reservations) {
            reservationsApi.add(convertReservationToReservationApi(reservation));
        }
        return reservationsApi;
    }

    com.bibliotheque.api.model.Reservation convertReservationApiToReservation(Reservation reservationApi) {
        com.bibliotheque.api.model.Reservation reservation = new com.bibliotheque.api.model.Reservation();

        reservation.setId(reservationApi.getId());
        reservation.setDateDebut(reservationApi.getDateDebut());
        reservation.setDateFin(reservationApi.getDateFin());
        reservation.setUtilisateur(utilisateurManagement.findById(reservationApi.getUtilisateurId()).get());
        reservation.setLivre(livreManagement.findById(reservationApi.getLivreId()).get());

        return reservation;
    }
}