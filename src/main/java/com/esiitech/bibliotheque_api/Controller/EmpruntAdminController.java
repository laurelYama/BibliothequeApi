package com.esiitech.bibliotheque_api.Controller;

import com.esiitech.bibliotheque_api.DTO.EmpruntDTO;
import com.esiitech.bibliotheque_api.Services.EmpruntService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/emprunts") // Route spécifique pour ADMIN
@PreAuthorize("hasRole('ADMIN')") // Sécurisation de toutes les routes pour les admins
public class EmpruntAdminController {

    private final EmpruntService empruntService;

    public EmpruntAdminController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    @GetMapping("/en-cours")
    public ResponseEntity<List<EmpruntDTO>> getEmpruntsEnCoursAdmin() {
        return ResponseEntity.ok(empruntService.getEmpruntsEnCours());
    }

    @GetMapping("/historique")
    public ResponseEntity<List<EmpruntDTO>> getHistoriqueEmpruntsAdmin() {
        return ResponseEntity.ok(empruntService.getHistoriqueEmprunts());
    }
}

