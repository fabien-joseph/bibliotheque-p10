package com.bibliotheque.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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
    private String nomDeCompte;
    @NotNull
    private String motDePasse;
    @NotNull
    private String prenom;
    @NotNull
    private String nom;
    @NotNull
    private OffsetDateTime dateCreation;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations = new ArrayList<>();
}
