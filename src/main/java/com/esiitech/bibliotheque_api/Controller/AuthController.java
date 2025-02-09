package com.esiitech.bibliotheque_api.Controller;

import com.esiitech.bibliotheque_api.DTO.AuthRequest;
import com.esiitech.bibliotheque_api.DTO.AuthResponse;
import com.esiitech.bibliotheque_api.DTO.UtilisateurDTO;
import com.esiitech.bibliotheque_api.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UtilisateurDTO utilisateurDTO) {
        System.out.println(utilisateurDTO);
        AuthResponse response = authService.register(utilisateurDTO);
        return ResponseEntity.status(201).body(response);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
