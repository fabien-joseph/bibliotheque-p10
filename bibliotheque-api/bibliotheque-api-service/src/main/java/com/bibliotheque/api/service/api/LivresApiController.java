package com.bibliotheque.api.service.api;

import com.bibliotheque.api.business.LivreManagement;
import com.bibliotheque.api.business.UtilisateurManagement;
import com.bibliotheque.api.model.Utilisateur;
import com.bibliotheque.api.service.model.Livre;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-11T23:01:18.100Z")

@Controller
public class LivresApiController implements LivresApi {

    private static final Logger log = LoggerFactory.getLogger(LivresApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private LivreManagement livreManagement;

    @Autowired
    private UtilisateurManagement utilisateurManagement;

    @org.springframework.beans.factory.annotation.Autowired
    public LivresApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<Void> addLivre(@ApiParam(value = "Un objet Livre doit être envoyé pour être ajouté", required = true) @Valid @RequestBody Livre body) {
        String accept = request.getHeader("Accept");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            if (utilisateurManagement.findUtilisateurByMail(((UserDetails) principal).getUsername()).isBibliothecaire()) {
                livreManagement.save(convertLivreApiToLivre(body));
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<Void> deleteLivre(@ApiParam(value = "ID du livre à supprimer", required = true) @PathVariable("livreId") Long livreId, @ApiParam(value = "") @RequestHeader(value = "api_key", required = false) String apiKey) {
        String accept = request.getHeader("Accept");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            if (utilisateurManagement.findUtilisateurByMail(((UserDetails) principal).getUsername()).isBibliothecaire()) {
                if (livreManagement.findById(livreId).isPresent()) {
                    livreManagement.deleteById(livreId);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Livre>> findLivres(@ApiParam(value = "Auteur ou nom du livre à trouver") @Valid @RequestParam(value = "search", required = false) String search) {
        search = search.toLowerCase();
        List<com.bibliotheque.api.model.Livre> livres = livreManagement.findLivresFilters(search);
        if (livres != null) {
            List<Livre> livresApi = convertListLivreToListLivreApi(livres);
            return new ResponseEntity<List<Livre>>(livresApi, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Livre> getLivreById(@ApiParam(value = "ID of livre to return", required = true) @PathVariable("livreId") Long livreId) {
        Optional<com.bibliotheque.api.model.Livre> livre = livreManagement.findById(livreId);
        if (livre.isPresent()) {
            Livre livreApi = convertLivreToLivreApi(livre.get());
            return new ResponseEntity<Livre>(livreApi, HttpStatus.OK);
        }
        return new ResponseEntity<Livre>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> updateLivre(@ApiParam(value = "ID du livre qui doit être mis à jour", required = true) @PathVariable("livreId") Long livreId, @ApiParam(value = "Mettre à jour le nom du livre") @RequestParam(value = "name", required = false) String name, @ApiParam(value = "Mettre à jour l'auteur d'un livre") @RequestParam(value = "auteur", required = false) String auteur, @ApiParam(value = "Mettre à jour la quantité d'un livre") @RequestParam(value = "quantite", required = false) Integer quantite) {
        String accept = request.getHeader("Accept");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            if (utilisateurManagement.findUtilisateurByMail(((UserDetails) principal).getUsername()).isBibliothecaire()) {
                Optional<com.bibliotheque.api.model.Livre> livre = livreManagement.findById(livreId);
                if (livre.isPresent()) {
                    livre.get().setNom(name);
                    livre.get().setAuteur(auteur);
                    livre.get().setQuantite(quantite);
                    livreManagement.save(livre.get());
                    return new ResponseEntity<Void>(HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    Livre convertLivreToLivreApi(com.bibliotheque.api.model.Livre livre) {
        Livre livreApi = new com.bibliotheque.api.service.model.Livre();

        livreApi.setId(livre.getId());
        livreApi.setNom(livre.getNom());
        livreApi.setAnnee(livre.getAnnee());
        livreApi.setAuteur(livre.getAuteur());
        livreApi.setGenre(Livre.GenreEnum.valueOf(livre.getGenre().name()));
        livreApi.setImgUrl(livre.getImgUrl());
        livreApi.setQuantite(livre.getQuantite());
        livreApi.setResume(livre.getResume());
        return livreApi;
    }

    List<Livre> convertListLivreToListLivreApi(List<com.bibliotheque.api.model.Livre> livres) {
        List<com.bibliotheque.api.service.model.Livre> livresApi = new ArrayList<>();

        for (com.bibliotheque.api.model.Livre livre : livres) {
            livresApi.add(convertLivreToLivreApi(livre));
        }

        return livresApi;
    }

    Integer getEnumId(String enumString) {
        List<String> genreList = new ArrayList<>();
        int enumId;
        for (Livre.GenreEnum genreEnum : Livre.GenreEnum.values()) {
            genreList.add(genreEnum.name());
        }

        for (int i = 0; i < genreList.size(); i++) {
            if (genreList.get(i).equals(enumString)) {
                return i;
            }
        }
        return null;
    }

    com.bibliotheque.api.model.Livre convertLivreApiToLivre(Livre livreApi) {
        com.bibliotheque.api.model.Livre livre = new com.bibliotheque.api.model.Livre();

        livre.setId(livreApi.getId());
        livre.setNom(livreApi.getNom());
        livre.setAnnee(livreApi.getAnnee());
        livre.setAuteur(livreApi.getAuteur());
        livre.setGenre(com.bibliotheque.api.model.Livre.GenreEnum.valueOf(livreApi.getGenre().name()));
        livre.setImgUrl(livreApi.getImgUrl());
        livre.setQuantite(livreApi.getQuantite());
        livre.setResume(livreApi.getResume());
        return livre;
    }
}