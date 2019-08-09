package com.bibliotheque.api.service.api;

import com.bibliotheque.api.service.model.Reservation;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-05T10:23:06.042Z")

@Controller
public class ReservationApiController implements ReservationApi {

    private static final Logger log = LoggerFactory.getLogger(ReservationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ReservationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<Void> addReservation(@Valid Reservation body) {
        return null;
    }

    public ResponseEntity<Void> deleteReservation(@ApiParam(value = "ID du livre à supprimer",required=true) @PathVariable("reservationId") Long reservationId, @ApiParam(value = "" ) @RequestHeader(value="api_key", required=false) String apiKey) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Reservation>> findreservationsByNom(@NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "available, pending, sold") @Valid @RequestParam(value = "status", required = true) List<String> status) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Reservation>>(objectMapper.readValue("[ {  \"livreId\" : 62,  \"dateDebut\" : \"2019-07-11T23:58:02.000Z\",  \"id\" : 9,  \"dateFin\" : \"2019-08-11T23:58:02.000Z\"}, {  \"livreId\" : 62,  \"dateDebut\" : \"2019-07-11T23:58:02.000Z\",  \"id\" : 9,  \"dateFin\" : \"2019-08-11T23:58:02.000Z\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Reservation>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Reservation> getReservationById(@ApiParam(value = "ID of livre to return",required=true) @PathVariable("reservationId") Long reservationId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Reservation>(objectMapper.readValue("{  \"livreId\" : 62,  \"dateDebut\" : \"2019-07-11T23:58:02.000Z\",  \"id\" : 9,  \"dateFin\" : \"2019-08-11T23:58:02.000Z\"}", Reservation.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Reservation>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateReservationWithForm(@ApiParam(value = "ID du livre qui doit être mis à jour",required=true) @PathVariable("reservationId") Long reservationId,@ApiParam(value = "Mettre à jour le nom du livre") @RequestParam(value="name", required=false)  String name,@ApiParam(value = "Mettre à jour l'auteur d'un livre") @RequestParam(value="auteur", required=false)  String auteur,@ApiParam(value = "Mettre à jour la quantité d'un livre") @RequestParam(value="quantite", required=false)  Integer quantite) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
