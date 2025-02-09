package com.esiitech.bibliotheque_api.Entities;

import com.esiitech.bibliotheque_api.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom de l'utilisateur est obligatoire")
    private String nom;

    @Email(message = "L'email doit être valide")
    @NotNull(message = "L'email de l'utilisateur est obligatoire")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "Le mot de passe est obligatoire")
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le rôle de l'utilisateur est obligatoire")
    private Role role;



    public Long getId() {
        return id;
    }


    public @NotNull(message = "Le nom de l'utilisateur est obligatoire") String getNom() {
        return nom;
    }

    public @Email(message = "L'email doit être valide") @NotNull(message = "L'email de l'utilisateur est obligatoire") String getEmail() {
        return email;
    }

    public @NotNull(message = "Le mot de passe est obligatoire") String getMotDePasse() {
        return motDePasse;
    }

    public @NotNull(message = "Le rôle de l'utilisateur est obligatoire") Role getRole() {
        return role;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(@NotNull(message = "Le nom de l'utilisateur est obligatoire") String nom) {
        this.nom = nom;
    }

    public void setEmail(@Email(message = "L'email doit être valide") @NotNull(message = "L'email de l'utilisateur est obligatoire") String email) {
        this.email = email;
    }

    public void setMotDePasse(@NotNull(message = "Le mot de passe est obligatoire") String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setRole(@NotNull(message = "Le rôle de l'utilisateur est obligatoire") Role role) {
        this.role = role;
    }
}
