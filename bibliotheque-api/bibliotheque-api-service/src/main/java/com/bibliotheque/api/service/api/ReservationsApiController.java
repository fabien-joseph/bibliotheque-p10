package com.bibliotheque.api.service.api;

import com.bibliotheque.api.business.ReservationManagement;
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

    @org.springframework.beans.factory.annotation.Autowired
    public ReservationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addReservation(@ApiParam(value = "Un objet Reservation doit être envoyé pour être ajouté" ,required=true )  @Valid @RequestBody Reservation body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteReservation(@ApiParam(value = "ID du livre à supprimer",required=true) @PathVariable("reservationId") Long reservationId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Reservation>> findReservations(@ApiParam(value = "Réservations faites sur l'id d'un livre",required=true) @PathVariable("livreId") Long livreId, @ApiParam(value = "Réservations faites par un utilisateur",required=true) @PathVariable("utilisateurId") Long utilisateurId, @ApiParam(value = "Réservations faites au de là d'une date",required=true) @PathVariable("dateDebut") OffsetDateTime dateDebut, @ApiParam(value = "Réservations faites au de là d'une date",required=true) @PathVariable("dateFin") OffsetDateTime dateFin) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Reservation>>(objectMapper.readValue("[ {  \"livreId\" : 62,  \"dateDebut\" : \"2019-07-11T23:58:02.000Z\",  \"utilisateurId\" : 12,  \"id\" : 9,  \"dateFin\" : \"2019-08-11T23:58:02.000Z\"}, {  \"livreId\" : 62,  \"dateDebut\" : \"2019-07-11T23:58:02.000Z\",  \"utilisateurId\" : 12,  \"id\" : 9,  \"dateFin\" : \"2019-08-11T23:58:02.000Z\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Reservation>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Reservation> getReservationById(@ApiParam(value = "ID of livre to return",required=true) @PathVariable("reservationId") Long reservationId) {
        Optional<com.bibliotheque.api.model.Reservation> reservation = reservationManagement.findById(reservationId);

        if (reservation.isPresent()) {
                //return new ResponseEntity<Reservation>(reservation.get(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Reservation>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateReservation(@ApiParam(value = "ID de la réservation qui doit être mise à jour",required=true) @PathVariable("reservationId") Long reservationId,@ApiParam(value = "Mettre à jour le nom du livre") @RequestParam(value="livreId", required=false)  Long livreId,@ApiParam(value = "Mettre à jour l'auteur d'un livre") @RequestParam(value="utilisateurId", required=false)  Long utilisateurId,@ApiParam(value = "Mettre à jour la quantité d'un livre") @RequestParam(value="dateDebut", required=false)  OffsetDateTime dateDebut,@ApiParam(value = "Mettre à jour la quantité d'un livre") @RequestParam(value="dateFin", required=false)  OffsetDateTime dateFin) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}