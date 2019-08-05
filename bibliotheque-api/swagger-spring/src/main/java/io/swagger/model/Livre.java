package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Livre
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-05T10:23:06.042Z")

public class Livre   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("nom")
  private String nom = null;

  @JsonProperty("auteur")
  private String auteur = null;

  @JsonProperty("resume")
  private String resume = null;

  @JsonProperty("imgId")
  private String imgId = null;

  @JsonProperty("annee")
  private Integer annee = null;

  @JsonProperty("quantite")
  private Integer quantite = null;

  /**
   * Gets or Sets genre
   */
  public enum GenreEnum {
    BIOGRAPHIE("biographie"),
    
    CONTE("conte"),
    
    EPOPEE("epopee"),
    
    FANTASTIQUE("fantastique"),
    
    HORREUR("horreur"),
    
    NOUVELLE("nouvelle"),
    
    ROMAN("roman"),
    
    SCIENCEFICTION("scienceFiction");

    private String value;

    GenreEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static GenreEnum fromValue(String text) {
      for (GenreEnum b : GenreEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("genre")
  private GenreEnum genre = null;

  public Livre id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Livre nom(String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * Get nom
   * @return nom
  **/
  @ApiModelProperty(example = "Harry Potter", value = "")


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Livre auteur(String auteur) {
    this.auteur = auteur;
    return this;
  }

  /**
   * Get auteur
   * @return auteur
  **/
  @ApiModelProperty(example = "J. K. Rowling", value = "")


  public String getAuteur() {
    return auteur;
  }

  public void setAuteur(String auteur) {
    this.auteur = auteur;
  }

  public Livre resume(String resume) {
    this.resume = resume;
    return this;
  }

  /**
   * Get resume
   * @return resume
  **/
  @ApiModelProperty(example = "Un jeune sorcier part à l'aventure dans un monde magique", value = "")


  public String getResume() {
    return resume;
  }

  public void setResume(String resume) {
    this.resume = resume;
  }

  public Livre imgId(String imgId) {
    this.imgId = imgId;
    return this;
  }

  /**
   * Get imgId
   * @return imgId
  **/
  @ApiModelProperty(example = "Un jeune sorcier part à l'aventure dans un monde magique", value = "")


  public String getImgId() {
    return imgId;
  }

  public void setImgId(String imgId) {
    this.imgId = imgId;
  }

  public Livre annee(Integer annee) {
    this.annee = annee;
    return this;
  }

  /**
   * Get annee
   * @return annee
  **/
  @ApiModelProperty(example = "2007", value = "")


  public Integer getAnnee() {
    return annee;
  }

  public void setAnnee(Integer annee) {
    this.annee = annee;
  }

  public Livre quantite(Integer quantite) {
    this.quantite = quantite;
    return this;
  }

  /**
   * Get quantite
   * @return quantite
  **/
  @ApiModelProperty(example = "52", value = "")


  public Integer getQuantite() {
    return quantite;
  }

  public void setQuantite(Integer quantite) {
    this.quantite = quantite;
  }

  public Livre genre(GenreEnum genre) {
    this.genre = genre;
    return this;
  }

  /**
   * Get genre
   * @return genre
  **/
  @ApiModelProperty(value = "")


  public GenreEnum getGenre() {
    return genre;
  }

  public void setGenre(GenreEnum genre) {
    this.genre = genre;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Livre livre = (Livre) o;
    return Objects.equals(this.id, livre.id) &&
        Objects.equals(this.nom, livre.nom) &&
        Objects.equals(this.auteur, livre.auteur) &&
        Objects.equals(this.resume, livre.resume) &&
        Objects.equals(this.imgId, livre.imgId) &&
        Objects.equals(this.annee, livre.annee) &&
        Objects.equals(this.quantite, livre.quantite) &&
        Objects.equals(this.genre, livre.genre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nom, auteur, resume, imgId, annee, quantite, genre);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Livre {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    auteur: ").append(toIndentedString(auteur)).append("\n");
    sb.append("    resume: ").append(toIndentedString(resume)).append("\n");
    sb.append("    imgId: ").append(toIndentedString(imgId)).append("\n");
    sb.append("    annee: ").append(toIndentedString(annee)).append("\n");
    sb.append("    quantite: ").append(toIndentedString(quantite)).append("\n");
    sb.append("    genre: ").append(toIndentedString(genre)).append("\n");
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

