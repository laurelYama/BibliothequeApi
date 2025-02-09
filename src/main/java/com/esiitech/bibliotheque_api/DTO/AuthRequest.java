package com.esiitech.bibliotheque_api.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String motDePasse;


    public @NotBlank(message = "L'email est obligatoire") @Email(message = "L'email doit être valide") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Le mot de passe est obligatoire") String getMotDePasse() {
        return motDePasse;
    }


    public AuthRequest(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }
}
