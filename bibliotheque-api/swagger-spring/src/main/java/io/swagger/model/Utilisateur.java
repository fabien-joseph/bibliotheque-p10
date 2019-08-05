package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Utilisateur
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-05T10:23:06.042Z")

public class Utilisateur   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("nomDeCompte")
  private String nomDeCompte = null;

  @JsonProperty("motDePasse")
  private String motDePasse = null;

  @JsonProperty("prenom")
  private String prenom = null;

  @JsonProperty("nom")
  private String nom = null;

  @JsonProperty("dateCreation")
  private OffsetDateTime dateCreation = null;

  public Utilisateur id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "9", value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Utilisateur nomDeCompte(String nomDeCompte) {
    this.nomDeCompte = nomDeCompte;
    return this;
  }

  /**
   * Get nomDeCompte
   * @return nomDeCompte
  **/
  @ApiModelProperty(example = "SuperFab24", value = "")


  public String getNomDeCompte() {
    return nomDeCompte;
  }

  public void setNomDeCompte(String nomDeCompte) {
    this.nomDeCompte = nomDeCompte;
  }

  public Utilisateur motDePasse(String motDePasse) {
    this.motDePasse = motDePasse;
    return this;
  }

  /**
   * Get motDePasse
   * @return motDePasse
  **/
  @ApiModelProperty(example = "azerty123EstUnMauvaisMDP", value = "")


  public String getMotDePasse() {
    return motDePasse;
  }

  public void setMotDePasse(String motDePasse) {
    this.motDePasse = motDePasse;
  }

  public Utilisateur prenom(String prenom) {
    this.prenom = prenom;
    return this;
  }

  /**
   * Get prenom
   * @return prenom
  **/
  @ApiModelProperty(example = "Fabien", value = "")


  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public Utilisateur nom(String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * Get nom
   * @return nom
  **/
  @ApiModelProperty(example = "Joseph", value = "")


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Utilisateur dateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
    return this;
  }

  /**
   * Get dateCreation
   * @return dateCreation
  **/
  @ApiModelProperty(example = "2019-07-11T23:58:02.000Z", value = "")

  @Valid

  public OffsetDateTime getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Utilisateur utilisateur = (Utilisateur) o;
    return Objects.equals(this.id, utilisateur.id) &&
        Objects.equals(this.nomDeCompte, utilisateur.nomDeCompte) &&
        Objects.equals(this.motDePasse, utilisateur.motDePasse) &&
        Objects.equals(this.prenom, utilisateur.prenom) &&
        Objects.equals(this.nom, utilisateur.nom) &&
        Objects.equals(this.dateCreation, utilisateur.dateCreation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nomDeCompte, motDePasse, prenom, nom, dateCreation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Utilisateur {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nomDeCompte: ").append(toIndentedString(nomDeCompte)).append("\n");
    sb.append("    motDePasse: ").append(toIndentedString(motDePasse)).append("\n");
    sb.append("    prenom: ").append(toIndentedString(prenom)).append("\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    dateCreation: ").append(toIndentedString(dateCreation)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

