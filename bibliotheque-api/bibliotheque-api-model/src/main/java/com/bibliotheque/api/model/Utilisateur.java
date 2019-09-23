package com.bibliotheque.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(length = 2000)
    private String mail;
    @NotNull
    private String motDePasse;
    @NotNull
    @Column(length = 200)
    private String prenom;
    @NotNull
    @Column(length = 200)
    private String nom;
    @NotNull
    private DateTime dateCreation;
    @NotNull
    private boolean isBibliothecaire;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations = new ArrayList<>();
}
