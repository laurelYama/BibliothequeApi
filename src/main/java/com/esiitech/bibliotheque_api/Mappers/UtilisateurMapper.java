package com.esiitech.bibliotheque_api.Mappers;

import com.esiitech.bibliotheque_api.DTO.UtilisateurDTO;
import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilisateurMapper {

    public static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static UtilisateurDTO toDTO(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());
        dto.setRole(utilisateur.getRole());
        return dto;
    }

    public static Utilisateur toEntity(UtilisateurDTO dto) {
        if (dto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setNom(dto.getNom());
        utilisateur.setEmail(dto.getEmail());

        // Hasher le mot de passe avant de l'enregistrer !
        utilisateur.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));

        utilisateur.setRole(dto.getRole());
        return utilisateur;
    }
}
