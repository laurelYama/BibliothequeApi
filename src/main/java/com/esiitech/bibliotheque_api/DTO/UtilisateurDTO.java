package com.esiitech.bibliotheque_api.DTO;

import com.esiitech.bibliotheque_api.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UtilisateurDTO {

    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String motDePasse;

    private Role role;

    public UtilisateurDTO() {
    }

    public UtilisateurDTO(Long id, String nom, String email, Role role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.role = role;
    }


    public @NotBlank(message = "Le nom est obligatoire") String getNom() {
        return nom;
    }

    public @NotBlank(message = "L'email est obligatoire") @Email(message = "L'email doit être valide") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Le mot de passe est obligatoire") @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères") String getMotDePasse() {
        return motDePasse;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setNom(@NotBlank(message = "Le nom est obligatoire") String nom) {
        this.nom = nom;
    }

    public void setEmail(@NotBlank(message = "L'email est obligatoire") @Email(message = "L'email doit être valide") String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
