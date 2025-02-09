package com.esiitech.bibliotheque_api.Controller;

import com.esiitech.bibliotheque_api.DTO.EmpruntDTO;
import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import com.esiitech.bibliotheque_api.Repositories.UtilisateurRepository;
import com.esiitech.bibliotheque_api.Services.EmpruntService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/emprunts") // Route spécifique pour USER
public class EmpruntUserController {

    private final EmpruntService empruntService;
    private final UtilisateurRepository utilisateurRepository;

    public EmpruntUserController(EmpruntService empruntService, UtilisateurRepository utilisateurRepository) {
        this.empruntService = empruntService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    public ResponseEntity<List<EmpruntDTO>> getEmprunts(@AuthenticationPrincipal UserDetails userDetails) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return ResponseEntity.ok(empruntService.getEmpruntsByUser(utilisateur.getId()));
    }

    @PostMapping
    public ResponseEntity<EmpruntDTO> createEmprunt(@RequestBody @Valid EmpruntDTO empruntDTO,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return ResponseEntity.ok(empruntService.createEmprunt(empruntDTO, utilisateur.getId()));
    }

    @PutMapping("/{id}/retourner")
    public ResponseEntity<EmpruntDTO> retournerLivre(@PathVariable Long id,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return ResponseEntity.ok(empruntService.retournerLivre(id, utilisateur.getId()));
    }

    @GetMapping("/historique")
    public ResponseEntity<List<EmpruntDTO>> getHistoriqueEmprunts(@AuthenticationPrincipal UserDetails userDetails) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return ResponseEntity.ok(empruntService.getHistoriqueEmpruntsByUser(utilisateur.getId()));
    }
}
