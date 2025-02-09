package com.esiitech.bibliotheque_api.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore les champs null dans la réponse JSON
public class LivreDTO {
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    @NotBlank(message = "L'auteur est obligatoire")
    private String auteur;

    @NotBlank(message = "L'ISBN est obligatoire")
    private String isbn;

    @Min(value = 1, message = "Le nombre d'exemplaires doit être au moins 1")
    private int nombreExemplaires;


    public LivreDTO() {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.nombreExemplaires = nombreExemplaires;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "Le titre est obligatoire") String getTitre() {
        return titre;
    }

    public @NotBlank(message = "L'auteur est obligatoire") String getAuteur() {
        return auteur;
    }

    public @NotBlank(message = "L'ISBN est obligatoire") String getIsbn() {
        return isbn;
    }

    @Min(value = 1, message = "Le nombre d'exemplaires doit être au moins 1")
    public int getNombreExemplaires() {
        return nombreExemplaires;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(@NotBlank(message = "Le titre est obligatoire") String titre) {
        this.titre = titre;
    }

    public void setAuteur(@NotBlank(message = "L'auteur est obligatoire") String auteur) {
        this.auteur = auteur;
    }

    public void setIsbn(@NotBlank(message = "L'ISBN est obligatoire") String isbn) {
        this.isbn = isbn;
    }

    public void setNombreExemplaires(@Min(value = 1, message = "Le nombre d'exemplaires doit être au moins 1") int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }
}
