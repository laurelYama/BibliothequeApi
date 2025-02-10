package com.esiitech.bibliotheque_api.Services;

import com.esiitech.bibliotheque_api.DTO.UtilisateurDTO;
import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import com.esiitech.bibliotheque_api.Exception.BadRequestException;
import com.esiitech.bibliotheque_api.Exception.NotFoundException;
import com.esiitech.bibliotheque_api.Repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    // Injection via constructeur
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur getById(Long id) {
        return utilisateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'email : " + email));
    }

    // Supprimer un utilisateur - réservé à l'ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public List<UtilisateurDTO> getAllUtilisateurs() {
        return utilisateurRepository.findAll()
                .stream()
                .map(utilisateur -> new UtilisateurDTO(
                        utilisateur.getId(),
                        utilisateur.getNom(),
                        utilisateur.getEmail(),
                        utilisateur.getRole()
                ))
                .collect(Collectors.toList());
    }


    public UtilisateurDTO getUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (utilisateur == null) {
            return null;
        }
        return new UtilisateurDTO(utilisateur.getId(), utilisateur.getNom(), utilisateur.getEmail(), utilisateur.getRole());
    }

    @PreAuthorize("hasRole('ADMIN')") // Seul un admin peut modifier un utilisateur
    @Transactional
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        Utilisateur existingUtilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));

        // Vérifier si l'email est déjà utilisé par un autre utilisateur
        if (!existingUtilisateur.getEmail().equals(utilisateur.getEmail()) &&
                utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new BadRequestException("Cet email est déjà utilisé !");
        }

        // Mise à jour des champs
        existingUtilisateur.setNom(utilisateur.getNom());
        existingUtilisateur.setEmail(utilisateur.getEmail());

        return utilisateurRepository.save(existingUtilisateur);
    }
}
