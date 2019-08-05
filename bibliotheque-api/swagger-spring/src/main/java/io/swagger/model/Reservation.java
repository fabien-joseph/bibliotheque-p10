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
 * Reservation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-05T10:23:06.042Z")

public class Reservation   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("livreId")
  private Long livreId = null;

  @JsonProperty("dateDebut")
  private OffsetDateTime dateDebut = null;

  @JsonProperty("dateFin")
  private OffsetDateTime dateFin = null;

  public Reservation id(Long id) {
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

  public Reservation livreId(Long livreId) {
    this.livreId = livreId;
    return this;
  }

  /**
   * Get livreId
   * @return livreId
  **/
  @ApiModelProperty(example = "62", value = "")


  public Long getLivreId() {
    return livreId;
  }

  public void setLivreId(Long livreId) {
    this.livreId = livreId;
  }

  public Reservation dateDebut(OffsetDateTime dateDebut) {
    this.dateDebut = dateDebut;
    return this;
  }

  /**
   * Get dateDebut
   * @return dateDebut
  **/
  @ApiModelProperty(example = "2019-07-11T23:58:02.000Z", value = "")

  @Valid

  public OffsetDateTime getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(OffsetDateTime dateDebut) {
    this.dateDebut = dateDebut;
  }

  public Reservation dateFin(OffsetDateTime dateFin) {
    this.dateFin = dateFin;
    return this;
  }

  /**
   * Get dateFin
   * @return dateFin
  **/
  @ApiModelProperty(example = "2019-08-11T23:58:02.000Z", value = "")

  @Valid

  public OffsetDateTime getDateFin() {
    return dateFin;
  }

  public void setDateFin(OffsetDateTime dateFin) {
    this.dateFin = dateFin;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reservation reservation = (Reservation) o;
    return Objects.equals(this.id, reservation.id) &&
        Objects.equals(this.livreId, reservation.livreId) &&
        Objects.equals(this.dateDebut, reservation.dateDebut) &&
        Objects.equals(this.dateFin, reservation.dateFin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, livreId, dateDebut, dateFin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Reservation {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    livreId: ").append(toIndentedString(livreId)).append("\n");
    sb.append("    dateDebut: ").append(toIndentedString(dateDebut)).append("\n");
    sb.append("    dateFin: ").append(toIndentedString(dateFin)).append("\n");
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

