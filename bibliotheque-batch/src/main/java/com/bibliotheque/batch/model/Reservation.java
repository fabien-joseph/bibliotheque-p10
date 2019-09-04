package com.bibliotheque.batch.model;

import io.swagger.client.model.Livre;
import io.swagger.client.model.Utilisateur;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {
    private Long id;
    private Boolean renouvelable;
    private Boolean rendu;
    private Long dateDebut;
    private Long dateFin;
    private Livre livre;
    private Utilisateur utilisateur;
}
