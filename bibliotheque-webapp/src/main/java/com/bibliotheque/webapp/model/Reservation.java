package com.bibliotheque.webapp.model;

import io.swagger.client.model.Livre;
import io.swagger.client.model.Utilisateur;
import org.joda.time.DateTime;

public class Reservation {
    private Long id;
    private DateTime dateDebut;
    private DateTime dateFin;
    private Livre livre;
    private Utilisateur utilisateur;

    public Reservation() {
    }


    public Reservation(Long id, DateTime dateDebut, DateTime dateFin, Livre livre, Utilisateur utilisateur) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.livre = livre;
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(DateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public DateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(DateTime dateFin) {
        this.dateFin = dateFin;
    }
}
