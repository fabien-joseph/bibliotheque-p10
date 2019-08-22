package com.bibliotheque.api.service.api;

import com.bibliotheque.api.business.UtilisateurManagement;
import com.bibliotheque.api.service.model.Utilisateur;
import org.joda.time.DateTime;
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

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-11T23:01:18.100Z")

@Controller
public class UtilisateursApiController implements UtilisateursApi {

    private static final Logger log = LoggerFactory.getLogger(UtilisateursApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    UtilisateurManagement utilisateurManagement;

    @org.springframework.beans.factory.annotation.Autowired
    public UtilisateursApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addUtilisateur(@ApiParam(value = "Un objet Reservation doit être envoyé pour être ajouté" ,required=true )  @Valid @RequestBody Utilisateur body) {
        String accept = request.getHeader("Accept");
        if (utilisateurManagement.findUtilisateurByMail(body.getMail()).isEmpty()) {
            utilisateurManagement.save(convertUtilisateurApiToUtilisateur(body));
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    public ResponseEntity<Void> deleteUtilisateur(@ApiParam(value = "ID de l'utilisateur à supprimer",required=true) @PathVariable("utilisateurId") Long utilisateurId) {
        String accept = request.getHeader("Accept");
        if (utilisateurManagement.findById(utilisateurId).isPresent()) {
            utilisateurManagement.deleteById(utilisateurId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Utilisateur>> findUtilisateursByMail(@NotNull @ApiParam(value = "Trouver un compte par mail", required = true) @Valid @RequestParam(value = "mail", required = true) String mail) {
        String accept = request.getHeader("Accept");
        List<com.bibliotheque.api.model.Utilisateur> utilisateurs = utilisateurManagement.findUtilisateurByMail(mail);
        if (utilisateurs.size() >  0) {
            return new ResponseEntity<List<Utilisateur>>(convertListUtilisateurToListUtilisateurApi(utilisateurs), HttpStatus.OK);
        }
        return new ResponseEntity<List<Utilisateur>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Utilisateur> getUtilisateurById(@ApiParam(value = "ID of livre to return",required=true) @PathVariable("utilisateurId") Long utilisateurId) {
        String accept = request.getHeader("Accept");
        Optional<com.bibliotheque.api.model.Utilisateur> utilisateur = utilisateurManagement.findById(utilisateurId);
        if (utilisateur.isPresent()) {
            return new ResponseEntity<Utilisateur>(convertUtilisateurToUtilisateurApi(utilisateur.get()), HttpStatus.OK);
        }
        return new ResponseEntity<Utilisateur>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateUtilisateurWithForm(@ApiParam(value = "ID de l'utilisateur qui doit être mis à jour",required=true) @PathVariable("utilisateurId") Long utilisateurId,@ApiParam(value = "Mettre à jour le mail de l'utilisateur") @RequestParam(value="mail", required=false)  String mail,@ApiParam(value = "Mettre à jour le mot de passe de l'utilisateur") @RequestParam(value="motDePasse", required=false)  String motDePasse,@ApiParam(value = "Mettre à jour le prenom de l'utilisateur") @RequestParam(value="prenom", required=false)  String prenom,@ApiParam(value = "Mettre à jour le prenom de l'utilisateur") @RequestParam(value="nom", required=false)  String nom,@ApiParam(value = "Mettre à jour le prenom de l'utilisateur") @RequestParam(value="dateCreation", required=false) DateTime dateCreation) {
        String accept = request.getHeader("Accept");
        Optional<com.bibliotheque.api.model.Utilisateur> utilisateur = utilisateurManagement.findById(utilisateurId);
        if (utilisateur.isPresent()) {
            utilisateur.get().setId(utilisateurId);
            utilisateur.get().setMotDePasse(motDePasse);
            utilisateur.get().setMail(mail);
            utilisateur.get().setPrenom(prenom);
            utilisateur.get().setDateCreation(dateCreation);
            utilisateur.get().setNom(nom);
            utilisateurManagement.save(utilisateur.get());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    Utilisateur convertUtilisateurToUtilisateurApi (com.bibliotheque.api.model.Utilisateur utilisateur) {
        Utilisateur utilisateurApi = new Utilisateur();

        utilisateurApi.setId(utilisateur.getId());
        utilisateurApi.setDateCreation(utilisateur.getDateCreation());
        utilisateurApi.setMail(utilisateur.getMail());
        utilisateurApi.setMotDePasse(utilisateur.getMotDePasse());
        utilisateurApi.setNom(utilisateur.getNom());
        utilisateurApi.setPrenom(utilisateur.getPrenom());
        return utilisateurApi;
    }

    List<Utilisateur> convertListUtilisateurToListUtilisateurApi(List<com.bibliotheque.api.model.Utilisateur> utilisateurs) {
        List<Utilisateur> utilisateursApi = new ArrayList<>();

        for (com.bibliotheque.api.model.Utilisateur utilisateur : utilisateurs) {
            utilisateursApi.add(convertUtilisateurToUtilisateurApi(utilisateur));
        }
        return utilisateursApi;
    }

    com.bibliotheque.api.model.Utilisateur convertUtilisateurApiToUtilisateur(Utilisateur utilisateurApi) {
        com.bibliotheque.api.model.Utilisateur utilisateur = new com.bibliotheque.api.model.Utilisateur();

        utilisateur.setId(utilisateurApi.getId());
        utilisateur.setNom(utilisateurApi.getNom());
        utilisateur.setPrenom(utilisateurApi.getPrenom());
        utilisateur.setDateCreation(utilisateurApi.getDateCreation());
        utilisateur.setMail(utilisateurApi.getMail());
        utilisateur.setMotDePasse(utilisateurApi.getMotDePasse());
        return utilisateur;
    }

}