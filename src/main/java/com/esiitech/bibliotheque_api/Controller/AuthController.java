package com.esiitech.bibliotheque_api.Controller;

import com.esiitech.bibliotheque_api.DTO.AuthRequest;
import com.esiitech.bibliotheque_api.DTO.AuthResponse;
import com.esiitech.bibliotheque_api.DTO.UtilisateurDTO;
import com.esiitech.bibliotheque_api.Services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Authentification", description = "Gestion de l'authentification et des utilisateurs")
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Enregistrer un nouvel utilisateur",
            description = "Permet à un administrateur d'ajouter un nouvel utilisateur avec un rôle spécifique."
    )
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UtilisateurDTO utilisateurDTO) {
        System.out.println(utilisateurDTO);
        AuthResponse response = authService.register(utilisateurDTO);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(
            summary = "Connexion utilisateur",
            description = "Permet à un utilisateur de se connecter avec ses identifiants et d'obtenir un token JWT."
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
