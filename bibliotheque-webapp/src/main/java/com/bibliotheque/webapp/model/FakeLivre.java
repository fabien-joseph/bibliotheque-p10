package com.bibliotheque.webapp.model;

public class FakeLivre {
    private Long id = null;
    private String nom = null;
    private String auteur = null;
    private Integer quantite = null;
    private String imgUrl = null;

    public FakeLivre() {
    }

    public FakeLivre(Long id, String nom, String auteur, Integer quantite, String imgUrl) {
        this.id = id;
        this.nom = nom;
        this.auteur = auteur;
        this.quantite = quantite;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "FakeLivre{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", auteur='" + auteur + '\'' +
                ", quantite=" + quantite +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
