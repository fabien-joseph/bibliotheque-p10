package com.bibliotheque.api.service.api;

import com.bibliotheque.api.business.LivreManagement;
import com.bibliotheque.api.business.ReservationManagement;
import com.bibliotheque.api.business.UtilisateurManagement;
import com.bibliotheque.api.model.Livre;
import com.bibliotheque.api.model.Utilisateur;
import com.bibliotheque.api.service.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.joda.time.DateTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
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

    private Authentication authentication;

    @org.springframework.beans.factory.annotation.Autowired
    public ReservationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addReservation(
            @ApiParam(value = "Un objet Reservation doit être envoyé pour être ajouté", required = true)
            @Valid @RequestBody Reservation body,
            @ApiParam(value = "Envoie de l'utilisateur faisant le requête", required = true)
            @RequestHeader(value = "Authorization", required = true) String authorization) {

        String accept = request.getHeader("Accept");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        Utilisateur utilisateurLog = utilisateurManagement.findUtilisateurByMail(((UserDetails) principal).getUsername());
        if (!utilisateurLog.isBibliothecaire() && utilisateurLog.getId() != body.getUtilisateurId()) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        if (livreManagement.findById(body.getLivreId()) == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            if (reservationManagement.canBeSaved(convertReservationApiToReservation(body))) {
                reservationManagement.save(convertReservationApiToReservation(body));
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.CONFLICT);
            }
        }
    }

    public ResponseEntity<Void> comingBack(@ApiParam(value = "ID de la réservation qui doit être mise à jour", required = true) @PathVariable("reservationId") Long reservationId) {
        String accept = request.getHeader("Accept");
        if (reservationManagement.findById(reservationId).isPresent()) {
            com.bibliotheque.api.model.Reservation reservation = reservationManagement.findById(reservationId).get();
            reservation.setRendu(!reservation.isRendu());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> deleteReservation(@ApiParam(value = "ID du livre à supprimer", required = true) @PathVariable("reservationId") Long reservationId, @ApiParam(value = "Envoie de l'utilisateur faisant le requête", required = true) @RequestHeader(value = "Authorization", required = true) String authorization) {
        String accept = request.getHeader("Accept");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Optional<com.bibliotheque.api.model.Reservation> reservation = reservationManagement.findById(reservationId);
            Utilisateur utilisateurLog = utilisateurManagement.findUtilisateurByMail(((UserDetails) principal).getUsername());
            if (reservation.isPresent()) {
                if (utilisateurLog.isBibliothecaire() || utilisateurLog.getId() == reservation.get().getUtilisateur().getId()) {
                    reservationManagement.deleteById(reservationId);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
                }
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Reservation>> findReservations(@ApiParam(value = "Envoie de l'utilisateur faisant le requête", required = true) @RequestHeader(value = "Authorization", required = true) String authorization, @ApiParam(value = "Réservations faites sur l'id d'un livre") @Valid @RequestParam(value = "livreId", required = false) Long livreId, @ApiParam(value = "Réservations faites par un utilisateur") @Valid @RequestParam(value = "utilisateurId", required = false) Long utilisateurId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Utilisateur utilisateurLog = utilisateurManagement.findUtilisateurByMail(((UserDetails) principal).getUsername());
            boolean isAllowed = true;

            Livre livre = null;
            Utilisateur utilisateur = null;
            if (livreId != null)
                livre = livreManagement.findById(livreId).get();
            if (utilisateurId != null)
                utilisateur = utilisateurManagement.findById(utilisateurId).get();

            List<com.bibliotheque.api.model.Reservation> reservations =
                    reservationManagement.findActualReservations(livre, utilisateur);
            List<Reservation> reservationsApi = convertListReservationToListReservationApi(reservations);
            if (!utilisateurLog.isBibliothecaire()) {
                for (Reservation reservation : reservationsApi) {
                    if (reservation.getUtilisateurId() != utilisateurLog.getId()) {
                        isAllowed = false;
                    }
                }
            }
            if (reservations != null) {
                if (isAllowed) {
                    return new ResponseEntity<List<Reservation>>(reservationsApi, HttpStatus.OK);
                } else {
                    return new ResponseEntity<List<Reservation>>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<List<Reservation>>(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<Reservation> getReservationById(@ApiParam(value = "ID of livre to return", required = true) @PathVariable("reservationId") Long reservationId, @ApiParam(value = "Envoie de l'utilisateur faisant le requête", required = true) @RequestHeader(value = "Authorization", required = true) String authorization) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Utilisateur utilisateurLog = utilisateurManagement.findUtilisateurByMail(((UserDetails) principal).getUsername());
            if (reservationManagement.findById(reservationId).isPresent()) {
                Optional<com.bibliotheque.api.model.Reservation> reservation = reservationManagement.findById(reservationId);
                if (reservation.get().getUtilisateur().getId() == utilisateurLog.getId() || utilisateurLog.isBibliothecaire()) {
                    Reservation reservationApi = convertReservationToReservationApi(reservation.get());
                    return new ResponseEntity<Reservation>(reservationApi, HttpStatus.OK);
                } else {

                }
            } else {
                return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Reservation>(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<List<Reservation>> getReservationsToAlertOfAReturn() {
        List<com.bibliotheque.api.model.Reservation> reservationsToDelete = reservationManagement.findReservationsByAlertedTrue();
        reservationManagement.deleteList(reservationsToDelete);
        List<com.bibliotheque.api.model.Reservation> reservationsToAlert = reservationManagement.getReservationsToAlert();
        return new ResponseEntity<List<Reservation>>(convertListReservationToListReservationApi(reservationsToAlert), HttpStatus.OK);
    }

    public ResponseEntity<Void> renewReservation(@ApiParam(value = "ID de la réservation qui doit être mise à jour", required = true) @PathVariable("reservationId") Long reservationId, @ApiParam(value = "Envoie de l'utilisateur faisant le requête", required = true) @RequestHeader(value = "Authorization", required = true) String authorization) {
        String accept = request.getHeader("Accept");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date date = new Date();
        if (!(principal instanceof UserDetails)) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

        Utilisateur utilisateurLog = utilisateurManagement.findUtilisateurByMail(((UserDetails) principal).getUsername());
        Optional<com.bibliotheque.api.model.Reservation> reservation = reservationManagement.findById(reservationId);
        if (!reservation.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        if (reservation.get().isRendu() || !reservation.get().isRenouvelable() ||
                reservation.get().getDateDebut().plusDays(Integer.parseInt(System.getenv("RESERVATION_DUREE"))).getMillis() <= date.getTime()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (reservation.get().getUtilisateur().getId() == utilisateurLog.getId() || utilisateurLog.isBibliothecaire()) {
                reservationManagement.renew(reservationId);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
            }
        }
    }

    public ResponseEntity<Void> toggleRenouvelableReservation(@ApiParam(value = "ID de la réservation qui doit être mise à jour", required = true) @PathVariable("reservationId") Long reservationId) {
        String accept = request.getHeader("Accept");
        if (reservationManagement.findById(reservationId).isPresent()) {
            com.bibliotheque.api.model.Reservation reservation = reservationManagement.findById(reservationId).get();
            reservation.setRenouvelable(!reservation.isRenouvelable());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Reservation>> getReservationsOfaBookWaiting(@NotNull @ApiParam(value = "Réservations faites sur l'id d'un livre", required = true) @Valid @RequestParam(value = "livreId", required = true) Long livreId) {
        Optional<Livre> livre = livreManagement.findById(livreId);
        if (livre.isPresent()) {
            List<com.bibliotheque.api.model.Reservation> reservations = reservationManagement.getReservationsOfaBookWaiting(livre.get());
            if (reservations != null) {
                return new ResponseEntity<List<Reservation>>(convertListReservationToListReservationApi(reservations), HttpStatus.OK);
            }
        }
        return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Reservation>> expiredReservation(@ApiParam(value = "Nombre de jour avant l'expiration") @Valid @RequestParam(value = "numberDay", required = false) Integer numberDay) {
        List<com.bibliotheque.api.model.Reservation> expiredReservations = reservationManagement.findReservationsSoonExpired(numberDay);
        if (expiredReservations != null) {
            return new ResponseEntity<List<Reservation>>(convertListReservationToListReservationApi(expiredReservations), HttpStatus.OK);
        }
        return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Reservation>> getReservationsOfaBookInProgress(@ApiParam(value = "Réservations faites sur l'id d'un livre", required = true) @PathVariable("livreId") Long livreId) {
        Optional<Livre> livre = livreManagement.findById(livreId);
        if (livre.isPresent()) {
            List<com.bibliotheque.api.model.Reservation> reservations = reservationManagement.getReservationsOfaBookInProgress(livre.get());
            if (reservations != null) {
                return new ResponseEntity<List<Reservation>>(convertListReservationToListReservationApi(reservations), HttpStatus.OK);
            }
        }
        return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> getReservationPlace(@ApiParam(value = "Id de la reservation pour laquelle on souhaite connaitre la position d'attente", required = true) @PathVariable("reservationId") Long reservationId) {
        Optional<com.bibliotheque.api.model.Reservation> reservation = reservationManagement.findById(reservationId);
        if (reservation.isPresent()) {
            Integer place = reservationManagement.getPlaceOfaReservationWaiting(reservation.get());
            return new ResponseEntity<Integer>(place, HttpStatus.OK);
        }
        return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
    }

    Reservation convertReservationToReservationApi(com.bibliotheque.api.model.Reservation reservation) {
        Reservation reservationApi = new Reservation();

        reservationApi.setId(reservation.getId());
        reservationApi.setDateCreation(reservation.getDateCreation().getMillis());
        reservationApi.setDateDebut((reservation.getDateDebut() != null) ? reservation.getDateDebut().getMillis() : null);
        reservationApi.setDateFin((reservation.getDateDebut() != null) ? reservation.getDateDebut().plusDays(Integer.parseInt(System.getenv("RESERVATION_DUREE"))).getMillis() : null);
        reservationApi.setDateRetourProche((reservation.getDateDebutPlusProche() != null) ? reservation.getDateDebutPlusProche().plusDays(Integer.parseInt(System.getenv("RESERVATION_DUREE"))).getMillis() : null);
        reservationApi.setLivreId(reservation.getLivre().getId());
        reservationApi.setUtilisateurId(reservation.getUtilisateur().getId());
        reservationApi.setRendu(reservation.isRendu());
        reservationApi.setRenouvelable(reservation.isRenouvelable());
        reservationApi.setAttente(reservation.isAttente());
        reservationApi.setAlerted(reservation.isAlerted());
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
        reservation.setDateDebut(new DateTime(reservationApi.getDateDebut()));
        reservation.setDateCreation(new DateTime(reservationApi.getDateCreation()));
        reservation.setUtilisateur(utilisateurManagement.findById(reservationApi.getUtilisateurId()).get());
        reservation.setLivre(livreManagement.findById(reservationApi.getLivreId()).get());
        reservation.setRendu(reservationApi.isRendu());
        reservation.setRenouvelable(reservationApi.isRenouvelable());
        reservation.setAttente(reservationApi.isAttente());
        reservation.setAlerted(reservationApi.isAlerted());

        return reservation;
    }
}