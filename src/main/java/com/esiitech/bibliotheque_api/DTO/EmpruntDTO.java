package com.esiitech.bibliotheque_api.DTO;

import com.esiitech.bibliotheque_api.Entities.Emprunt;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class EmpruntDTO {
    private Long id;

    @NotNull(message = "L'ID de l'utilisateur est obligatoire")
    private Long utilisateurId;

    @NotNull(message = "L'ID du livre est obligatoire")
    private Long livreId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateEmprunt;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateRetourPrevu;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateRetourEffectif;

    public EmpruntDTO(Emprunt emprunt) {
        this.id = emprunt.getId();
        this.livreId = emprunt.getLivre().getId();
        this.utilisateurId = emprunt.getUtilisateur().getId();
        this.dateEmprunt = emprunt.getDateEmprunt();
        this.dateRetourPrevu = emprunt.getDateRetourPrevu();
        this.dateRetourEffectif = emprunt.getDateRetourEffectif();
    }


    public Long getId() {
        return id;
    }

    public @NotNull(message = "L'ID de l'utilisateur est obligatoire") Long getUtilisateurId() {
        return utilisateurId;
    }

    public @NotNull(message = "L'ID du livre est obligatoire") Long getLivreId() {
        return livreId;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevu() {
        return dateRetourPrevu;
    }

    public LocalDate getDateRetourEffectif() {
        return dateRetourEffectif;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUtilisateurId(@NotNull(message = "L'ID de l'utilisateur est obligatoire") Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public void setLivreId(@NotNull(message = "L'ID du livre est obligatoire") Long livreId) {
        this.livreId = livreId;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateRetourPrevu(LocalDate dateRetourPrevu) {
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public void setDateRetourEffectif(LocalDate dateRetourEffectif) {
        this.dateRetourEffectif = dateRetourEffectif;
    }
}
