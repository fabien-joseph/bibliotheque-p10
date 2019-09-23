package com.bibliotheque.api.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(length = 200)
    private String nom;
    @NotNull
    @Column(length = 200)
    private String auteur;
    @NotNull
    @Column(length = 2000)
    private String resume;
    @NotNull
    @Column(length = 2000)
    private String imgUrl;
    @NotNull
    @Min(0)
    private Integer annee;
    @NotNull
    @Min(0)
    private Integer quantite;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "livre", cascade = CascadeType.REMOVE)
    List<Reservation> reservations = new ArrayList<>();

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
        public String toString() {
            return String.valueOf(value);
        }

        public static GenreEnum fromValue(String text) {
            for (GenreEnum b : GenreEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    private GenreEnum genre = null;
}
