package com.esiitech.bibliotheque_api.Controller;

import com.esiitech.bibliotheque_api.DTO.LivreDTO;
import com.esiitech.bibliotheque_api.Mappers.LivreMapper;
import com.esiitech.bibliotheque_api.Services.LivreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Tag(name = "Livres", description = "Gestion des livres de la bibliothèque")
@RequestMapping("/api/livres")
@SecurityRequirement(name = "BearerAuth")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Obtenir tous les livres", description = "Retourne la liste complète des livres disponibles dans la bibliothèque.")
    public ResponseEntity<List<LivreDTO>> getAllLivres() {
        List<LivreDTO> livres = livreService.getAllLivres();
        return ResponseEntity.ok(livres);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un livre par ID", description = "Retourne les détails d'un livre spécifique en fonction de son ID.")
    public ResponseEntity<LivreDTO> getLivreById(@PathVariable Long id) {
        return livreService.getLivreById(id)
                .map(livre -> ResponseEntity.ok(LivreMapper.toDTO(livre)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Créer un livre", description = "Ajoute un nouveau livre à la bibliothèque.")
    public ResponseEntity<LivreDTO> createLivre(@Valid @RequestBody LivreDTO livreDTO) {
        LivreDTO createdLivre = livreService.createLivre(livreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLivre);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un livre", description = "Met à jour les informations d'un livre existant.")
    public ResponseEntity<LivreDTO> modifierLivre(@PathVariable Long id, @Valid @RequestBody LivreDTO livreDTO) {
        try {
            LivreDTO updatedLivre = livreService.modifierLivre(id, livreDTO);
            return ResponseEntity.ok(updatedLivre);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un livre", description = "Supprime un livre en fonction de son ID.")
    public ResponseEntity<String> deleteLivre(@PathVariable Long id) {
        try {
            livreService.deleteLivre(id);
            return ResponseEntity.ok("Livre supprimé avec succès.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
