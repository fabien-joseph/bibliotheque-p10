package com.bibliotheque.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
    @Column(columnDefinition = "boolean default true")
    private boolean renouvelable;
    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean rendu;
    @NotNull
    @Column
    private boolean attente;

    @ManyToOne
    @NotNull
    private Livre livre;

    @ManyToOne
    @NotNull
    private Utilisateur utilisateur;
}
