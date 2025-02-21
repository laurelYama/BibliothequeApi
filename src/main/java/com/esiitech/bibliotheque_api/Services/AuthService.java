package com.esiitech.bibliotheque_api.Services;

import com.esiitech.bibliotheque_api.DTO.AuthRequest;
import com.esiitech.bibliotheque_api.DTO.AuthResponse;
import com.esiitech.bibliotheque_api.DTO.UtilisateurDTO;
import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import com.esiitech.bibliotheque_api.Repositories.UtilisateurRepository;
import com.esiitech.bibliotheque_api.Security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class AuthService {

    public AuthService(UtilisateurRepository utilisateurRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }



    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(UtilisateurDTO utilisateurDTO) {
        if (utilisateurRepository.findByEmail(utilisateurDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Cet email est déjà utilisé");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));

        if (utilisateurDTO.getRole() == null) {
            throw new IllegalArgumentException("Le rôle est requis");
        }

        utilisateur.setRole(utilisateurDTO.getRole());
        utilisateurRepository.save(utilisateur);

        // Génération du token
        String token = jwtUtil.generateToken(utilisateur.getEmail(), utilisateur.getRole().name());

        // Extraction et conversion en date
        Date expirationDate = jwtUtil.extractAllClaims(token).getExpiration();
        Long expirationTimestamp = (expirationDate != null) ? expirationDate.getTime() / 1000 : null;
        String expirationFormatted = formatExpirationDate(expirationDate);

        return new AuthResponse(token, expirationTimestamp, expirationFormatted);
    }

    public AuthResponse login(AuthRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (utilisateur == null || !passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new IllegalArgumentException("Email ou mot de passe incorrect");
        }

        // Génération du token
        String token = jwtUtil.generateToken(utilisateur.getEmail(), utilisateur.getRole().name());

        // Extraction et conversion en date
        Date expirationDate = jwtUtil.extractAllClaims(token).getExpiration();
        Long expirationTimestamp = (expirationDate != null) ? expirationDate.getTime() / 1000 : null;
        String expirationFormatted = formatExpirationDate(expirationDate);

        return new AuthResponse(token, expirationTimestamp, expirationFormatted);
    }

    private String formatExpirationDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }
}
