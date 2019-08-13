package com.bibliotheque.api.service.api;

import com.bibliotheque.api.business.LivreManagement;
import com.bibliotheque.api.service.convert.ModelsConvert;
import com.bibliotheque.api.service.model.Livre;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-11T23:01:18.100Z")

@Controller
public class LivresApiController implements LivresApi {

    private static final Logger log = LoggerFactory.getLogger(LivresApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final ModelsConvert modelsConvert = new ModelsConvert();

    @Autowired
    private LivreManagement livreManagement;

    @org.springframework.beans.factory.annotation.Autowired
    public LivresApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addLivre(@ApiParam(value = "Un objet Livre doit être envoyé pour être ajouté", required = true) @Valid @RequestBody Livre body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> addLivre(com.bibliotheque.api.model.@Valid Livre body) {
        return null;
    }

    public ResponseEntity<Void> deleteLivre(@ApiParam(value = "ID du livre à supprimer", required = true) @PathVariable("livreId") Long livreId, @ApiParam(value = "") @RequestHeader(value = "api_key", required = false) String apiKey) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<com.bibliotheque.api.model.Livre>> findLivres() {
        List<com.bibliotheque.api.model.Livre> livres = livreManagement.findAll();
        System.out.println("Size api : " + livres.size());
        if (livres != null) {
            System.out.println("Ca passe");
            return new ResponseEntity<List<com.bibliotheque.api.model.Livre>>(livres, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<com.bibliotheque.api.model.Livre> getLivreById(@ApiParam(value = "ID of livre to return", required = true) @PathVariable("livreId") Long livreId) {
        Optional<com.bibliotheque.api.model.Livre> livre = livreManagement.findById(livreId);
        if (livre.isPresent())
            return new ResponseEntity<com.bibliotheque.api.model.Livre>(livre.get(), HttpStatus.OK);

        return new ResponseEntity<com.bibliotheque.api.model.Livre>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> updateLivre(@ApiParam(value = "ID du livre qui doit être mis à jour", required = true) @PathVariable("livreId") Long livreId, @ApiParam(value = "Mettre à jour le nom du livre") @RequestParam(value = "name", required = false) String name, @ApiParam(value = "Mettre à jour l'auteur d'un livre") @RequestParam(value = "auteur", required = false) String auteur, @ApiParam(value = "Mettre à jour la quantité d'un livre") @RequestParam(value = "quantite", required = false) Integer quantite) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
}