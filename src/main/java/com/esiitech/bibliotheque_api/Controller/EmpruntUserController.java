package com.esiitech.bibliotheque_api.Controller;

import com.esiitech.bibliotheque_api.DTO.EmpruntDTO;
import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import com.esiitech.bibliotheque_api.Repositories.UtilisateurRepository;
import com.esiitech.bibliotheque_api.Services.EmpruntService;
import com.esiitech.bibliotheque_api.Services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Emprunts - Utilisateur", description = "Gestion des emprunts de livres")
@RequestMapping("/api/user/emprunts") // Route spécifique pour USER
@SecurityRequirement(name = "BearerAuth")
public class EmpruntUserController {

    private final EmpruntService empruntService;
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurService utilisateurService;

    public EmpruntUserController(EmpruntService empruntService, UtilisateurRepository utilisateurRepository, UtilisateurService utilisateurService) {
        this.empruntService = empruntService;
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    @Operation(summary = "Obtenir les emprunts de l'utilisateur", description = "Retourne la liste des emprunts en cours pour l'utilisateur connecté.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des emprunts récupérée avec succès"),
            @ApiResponse(responseCode = "401", description = "Non autorisé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<List<EmpruntDTO>> getEmprunts(@AuthenticationPrincipal UserDetails userDetails) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return ResponseEntity.ok(empruntService.getEmpruntsByUser(utilisateur.getId()));
    }

    @PostMapping
    @Operation(summary = "Créer un emprunt", description = "Permet à un utilisateur d’emprunter un livre.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Emprunt créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "401", description = "Non autorisé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<EmpruntDTO> createEmprunt(@RequestBody @Valid EmpruntDTO empruntDTO,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return ResponseEntity.ok(empruntService.createEmprunt(empruntDTO, utilisateur.getId()));
    }

    @PutMapping("/{id}/retourner")
    @Operation(summary = "Retourner un livre", description = "Permet à un utilisateur de retourner un livre emprunté.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livre retourné avec succès"),
            @ApiResponse(responseCode = "404", description = "Emprunt non trouvé"),
            @ApiResponse(responseCode = "401", description = "Non autorisé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<EmpruntDTO> retournerLivre(@PathVariable Long id,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return ResponseEntity.ok(empruntService.retournerLivre(id, utilisateur.getId()));
    }

    @GetMapping("/historique")
    @Operation(summary = "Historique des emprunts", description = "Retourne l'historique des emprunts de l'utilisateur connecté.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historique récupéré avec succès"),
            @ApiResponse(responseCode = "401", description = "Non autorisé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<List<EmpruntDTO>> getHistoriqueEmprunts(@AuthenticationPrincipal UserDetails userDetails) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return ResponseEntity.ok(empruntService.getHistoriqueEmpruntsByUser(utilisateur.getId()));
    }

    @GetMapping("/{id}/retards")
    @Operation(summary = "Lister les emprunts en retard de l'utilisateur connecté",
            description = "Retourne tous les emprunts en retard de l'utilisateur actuellement authentifié.")
    public ResponseEntity<List<EmpruntDTO>> getEmpruntsEnRetard(@AuthenticationPrincipal UserDetails userDetails) {
        // Récupérer l'utilisateur connecté
        Utilisateur utilisateur = utilisateurService.findByEmail(userDetails.getUsername());

        return ResponseEntity.ok(empruntService.getEmpruntsEnRetard(utilisateur.getId()));
    }


}
