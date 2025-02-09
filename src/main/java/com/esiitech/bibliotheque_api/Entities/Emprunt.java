package com.esiitech.bibliotheque_api.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "emprunts")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;

    @NotNull(message = "La date d'emprunt est obligatoire")
    @FutureOrPresent(message = "La date d'emprunt ne peut pas être dans le futur")
    private LocalDate dateEmprunt;

    @NotNull(message = "La date de retour prévue est obligatoire")
    @FutureOrPresent(message = "La date de retour prévue ne peut pas être dans le passé")
    private LocalDate dateRetourPrevu;

    private LocalDate dateRetourEffectif;

    public Emprunt() {

    }

    public boolean isRendu() {
        return dateRetourEffectif != null;
    }

    public Emprunt(Long id, Utilisateur utilisateur, Livre livre, LocalDate dateEmprunt, LocalDate dateRetourPrevu, LocalDate dateRetourEffectif) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.livre = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevu = dateRetourPrevu;
        this.dateRetourEffectif = dateRetourEffectif;
    }

    public Long getId() {
        return id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Livre getLivre() {
        return livre;
    }

    public @NotNull(message = "La date d'emprunt est obligatoire") @FutureOrPresent(message = "La date d'emprunt ne peut pas être dans le futur") LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public @NotNull(message = "La date de retour prévue est obligatoire") @FutureOrPresent(message = "La date de retour prévue ne peut pas être dans le passé") LocalDate getDateRetourPrevu() {
        return dateRetourPrevu;
    }

    public LocalDate getDateRetourEffectif() {
        return dateRetourEffectif;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public void setDateEmprunt(@NotNull(message = "La date d'emprunt est obligatoire") @FutureOrPresent(message = "La date d'emprunt ne peut pas être dans le futur") LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateRetourPrevu(@NotNull(message = "La date de retour prévue est obligatoire") @FutureOrPresent(message = "La date de retour prévue ne peut pas être dans le passé") LocalDate dateRetourPrevu) {
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public void setDateRetourEffectif(LocalDate dateRetourEffectif) {
        this.dateRetourEffectif = dateRetourEffectif;
    }
}
