package com.esiitech.bibliotheque_api.Controller;

import com.esiitech.bibliotheque_api.DTO.UtilisateurDTO;
import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import com.esiitech.bibliotheque_api.Services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs de la bibliothèque")
@RequestMapping("/api/utilisateurs")
@SecurityRequirement(name = "BearerAuth")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un utilisateur", description = "Permet à un administrateur de supprimer un utilisateur par son ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtenir tous les utilisateurs", description = "Retourne la liste complète des utilisateurs enregistrés.")
    @GetMapping("/all")
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() {
        List<UtilisateurDTO> utilisateurs = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Obtenir un utilisateur par ID", description = "Retourne les informations d'un utilisateur spécifique en fonction de son ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable Long id) {
        UtilisateurDTO utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(utilisateur);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Mettre à jour un utilisateur", description = "Permet à un administrateur de modifier les informations d'un utilisateur spécifique.")
    @PutMapping("/update/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
        return ResponseEntity.ok(updatedUtilisateur);
    }
}
