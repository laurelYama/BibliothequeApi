package com.esiitech.bibliotheque_api.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "livres")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le titre du livre est obligatoire")
    private String titre;

    @NotNull(message = "L'auteur du livre est obligatoire")
    private String auteur;

    @NotNull(message = "Le ISBN est obligatoire")
    @Column(unique = true, nullable = false)
    private String isbn;

    @Positive(message = "Le nombre d'exemplaires doit être positif")
    private int nombreExemplaires;


    public Long getId() {
        return id;
    }

    public @NotNull(message = "Le titre du livre est obligatoire") String getTitre() {
        return titre;
    }

    public @NotNull(message = "L'auteur du livre est obligatoire") String getAuteur() {
        return auteur;
    }

    public @NotNull(message = "Le ISBN est obligatoire") String getIsbn() {
        return isbn;
    }

    @Positive(message = "Le nombre d'exemplaires doit être positif")
    public int getNombreExemplaires() {
        return nombreExemplaires;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(@NotNull(message = "Le titre du livre est obligatoire") String titre) {
        this.titre = titre;
    }

    public void setAuteur(@NotNull(message = "L'auteur du livre est obligatoire") String auteur) {
        this.auteur = auteur;
    }

    public void setIsbn(@NotNull(message = "Le ISBN est obligatoire") String isbn) {
        this.isbn = isbn;
    }

    public void setNombreExemplaires(@Positive(message = "Le nombre d'exemplaires doit être positif") int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }
}
