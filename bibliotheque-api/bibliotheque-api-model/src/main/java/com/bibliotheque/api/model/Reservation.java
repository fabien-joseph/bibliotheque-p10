package com.bibliotheque.api.model;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private DateTime dateDebut;
    @NotNull
    private DateTime dateFin;

    @ManyToOne
    private Livre livre;

    @ManyToOne
    private Utilisateur utilisateur;
}
