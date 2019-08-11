package com.bibliotheque.api.service.api;

import com.bibliotheque.api.service.model.Livre;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-11T23:01:18.100Z")

@Controller
public class LivresApiController implements LivresApi {

    private static final Logger log = LoggerFactory.getLogger(LivresApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LivresApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addLivre(@ApiParam(value = "Un objet Livre doit être envoyé pour être ajouté" ,required=true )  @Valid @RequestBody Livre body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteLivre(@ApiParam(value = "ID du livre à supprimer",required=true) @PathVariable("livreId") Long livreId,@ApiParam(value = "" ) @RequestHeader(value="api_key", required=false) String apiKey) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Livre>> findLivres(@ApiParam(value = "Mots contenus dans le nom du livre") @RequestParam(value="nom", required=false)  String nom, @ApiParam(value = "Nom de l'auteur à rechercher") @RequestParam(value="auteur", required=false)  String auteur, @ApiParam(value = "Année à rechercher") @RequestParam(value="annee", required=false)  Integer annee, @ApiParam(value = "Genre littéraire à rechercher", allowableValues="biographie, conte, epopee, fantastique, horreur, nouvelle, roman, scienceFiction") @RequestParam(value="genre", required=false)  String genre) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Livre>>(objectMapper.readValue("[ {  \"resume\" : \"Un jeune sorcier part à l'aventure dans un monde magique\",  \"imgUrl\" : \"Un jeune sorcier part à l'aventure dans un monde magique\",  \"genre\" : \"biographie\",  \"id\" : 0,  \"annee\" : 2007,  \"nom\" : \"Harry Potter\",  \"auteur\" : \"J. K. Rowling\",  \"quantite\" : 52}, {  \"resume\" : \"Un jeune sorcier part à l'aventure dans un monde magique\",  \"imgUrl\" : \"Un jeune sorcier part à l'aventure dans un monde magique\",  \"genre\" : \"biographie\",  \"id\" : 0,  \"annee\" : 2007,  \"nom\" : \"Harry Potter\",  \"auteur\" : \"J. K. Rowling\",  \"quantite\" : 52} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Livre>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Livre>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Livre> getLivreById(@ApiParam(value = "ID of livre to return",required=true) @PathVariable("livreId") Long livreId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Livre>(objectMapper.readValue("{  \"resume\" : \"Un jeune sorcier part à l'aventure dans un monde magique\",  \"imgUrl\" : \"Un jeune sorcier part à l'aventure dans un monde magique\",  \"genre\" : \"biographie\",  \"id\" : 0,  \"annee\" : 2007,  \"nom\" : \"Harry Potter\",  \"auteur\" : \"J. K. Rowling\",  \"quantite\" : 52}", Livre.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Livre>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Livre>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateLivre(@ApiParam(value = "ID du livre qui doit être mis à jour",required=true) @PathVariable("livreId") Long livreId,@ApiParam(value = "Mettre à jour le nom du livre") @RequestParam(value="name", required=false)  String name,@ApiParam(value = "Mettre à jour l'auteur d'un livre") @RequestParam(value="auteur", required=false)  String auteur,@ApiParam(value = "Mettre à jour la quantité d'un livre") @RequestParam(value="quantite", required=false)  Integer quantite) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}