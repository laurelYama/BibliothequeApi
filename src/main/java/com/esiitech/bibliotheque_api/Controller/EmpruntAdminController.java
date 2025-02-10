package com.esiitech.bibliotheque_api.Controller;

import com.esiitech.bibliotheque_api.DTO.EmpruntDTO;
import com.esiitech.bibliotheque_api.Services.EmpruntService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Emprunts - Administration", description = "Gestion des emprunts pour les administrateurs")
@RequestMapping("/api/admin/emprunts") // Route spécifique pour ADMIN
@PreAuthorize("hasRole('ADMIN')") // Sécurisation de toutes les routes pour les admins
@SecurityRequirement(name = "BearerAuth")
public class EmpruntAdminController {

    private final EmpruntService empruntService;

    public EmpruntAdminController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    @GetMapping("/en-cours")
    @Operation(summary = "Lister les emprunts en cours", description = "Retourne tous les emprunts en cours de la bibliothèque.")
    public ResponseEntity<List<EmpruntDTO>> getEmpruntsEnCoursAdmin() {
        return ResponseEntity.ok(empruntService.getEmpruntsEnCours());
    }

    @GetMapping("/historique")
    @Operation(summary = "Lister l'historique des emprunts", description = "Retourne l'historique complet des emprunts effectués.")
    public ResponseEntity<List<EmpruntDTO>> getHistoriqueEmpruntsAdmin() {
        return ResponseEntity.ok(empruntService.getHistoriqueEmprunts());
    }

    @GetMapping("/utilisateur/{utilisateurId}/en-cours")
    @Operation(summary = "Lister les emprunts en cours d'un utilisateur", description = "Retourne tous les emprunts en cours d'un utilisateur.")
    public ResponseEntity<List<EmpruntDTO>> getEmpruntsEnCoursParUtilisateur(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(empruntService.getEmpruntsEnCoursParUtilisateur(utilisateurId));
    }

    @GetMapping("/utilisateur/{utilisateurId}/historique")
    @Operation(summary = "Lister l'historique des emprunts d'un utilisateur", description = "Retourne l'historique complet des emprunts effectués par un utilisateur.")
    public ResponseEntity<List<EmpruntDTO>> getHistoriqueEmpruntsParUtilisateur(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(empruntService.getHistoriqueEmpruntsParUtilisateur(utilisateurId));
    }


    @GetMapping("/retards")
    @Operation(summary = "Lister tous les emprunts en retard", description = "Retourne tous les emprunts en retard de la bibliothèque.")
    public ResponseEntity<List<EmpruntDTO>> getEmpruntsEnRetardPourAdmin() {
        return ResponseEntity.ok(empruntService.getEmpruntsEnRetardPourAdmin());
    }

    @GetMapping("/utilisateur/{utilisateurId}/retards")
    @Operation(summary = "Lister les emprunts en retard d'un utilisateur", description = "Retourne tous les emprunts en retard d'un utilisateur spécifique.")
    public ResponseEntity<List<EmpruntDTO>> getEmpruntsEnRetardParUtilisateur(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(empruntService.getEmpruntsEnRetardParUtilisateurPourAdmin(utilisateurId));
    }

}
