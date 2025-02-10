package com.esiitech.bibliotheque_api.Services;

import com.esiitech.bibliotheque_api.DTO.EmpruntDTO;
import com.esiitech.bibliotheque_api.Entities.Emprunt;
import com.esiitech.bibliotheque_api.Entities.Livre;
import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import com.esiitech.bibliotheque_api.Exception.BadRequestException;
import com.esiitech.bibliotheque_api.Exception.NotFoundException;
import com.esiitech.bibliotheque_api.Mappers.EmpruntMapper;
import com.esiitech.bibliotheque_api.Repositories.EmpruntRepository;
import com.esiitech.bibliotheque_api.Repositories.UtilisateurRepository;
import com.esiitech.bibliotheque_api.Repositories.LivreRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final LivreRepository livreRepository;

    public EmpruntService(EmpruntRepository empruntRepository, UtilisateurRepository utilisateurRepository, LivreRepository livreRepository, EmpruntMapper empruntMapper) {
        this.empruntRepository = empruntRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.livreRepository = livreRepository;
    }

    // Récupérer tous les emprunts actifs d'un utilisateur
    public List<EmpruntDTO> getEmpruntsByUser(Long userId) {
        List<Emprunt> emprunts = empruntRepository.findByUtilisateurIdAndDateRetourEffectifIsNull(userId);
        return emprunts.stream().map(EmpruntDTO::new).collect(Collectors.toList());
    }

    // Création d'un emprunt
    @Transactional
    public EmpruntDTO createEmprunt(EmpruntDTO empruntDTO, Long userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));

        Livre livre = livreRepository.findById(empruntDTO.getLivreId())
                .orElseThrow(() -> new NotFoundException("Livre non trouvé"));

        // Vérifier si l'utilisateur a déjà emprunté ce livre
        if (empruntRepository.existsByUtilisateurIdAndLivreIdAndDateRetourEffectifIsNull(userId, livre.getId())) {
            throw new BadRequestException("Vous avez déjà emprunté ce livre et ne l'avez pas encore retourné !");
        }

        // Vérifier la disponibilité des exemplaires
        if (livre.getNombreExemplaires() <= 0) {
            throw new BadRequestException("Aucun exemplaire disponible pour ce livre !");
        }

        // Diminuer le nombre d'exemplaires disponibles
        livre.setNombreExemplaires(livre.getNombreExemplaires() - 1);
        livreRepository.save(livre);

        // Création de l'emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setUtilisateur(utilisateur);
        emprunt.setLivre(livre);
        emprunt.setDateEmprunt(LocalDate.now());

        // Prendre en compte la date fournie par l'utilisateur ou mettre une date par défaut (+1 mois)
        if (empruntDTO.getDateRetourPrevu() != null) {
            emprunt.setDateRetourPrevu(empruntDTO.getDateRetourPrevu());
        } else {
            emprunt.setDateRetourPrevu(LocalDate.now().plusMonths(1)); // Définir une date par défaut (1 mois après)
        }

        return new EmpruntDTO(empruntRepository.save(emprunt));
    }


    // Retourner un livre
    @Transactional
    public EmpruntDTO retournerLivre(Long empruntId, Long userId) {
        Emprunt emprunt = empruntRepository.findById(empruntId)
                .orElseThrow(() -> new NotFoundException("Emprunt non trouvé !"));

        // Vérifier que l'utilisateur est bien celui qui a emprunté le livre
        if (!emprunt.getUtilisateur().getId().equals(userId)) {
            throw new BadRequestException("Vous ne pouvez retourner que vos propres emprunts !");
        }

        // Vérifier que le livre n'a pas déjà été retourné
        if (emprunt.getDateRetourEffectif() != null) {
            throw new BadRequestException("Ce livre a déjà été retourné !");
        }

        // Mettre à jour la date de retour effectif
        emprunt.setDateRetourEffectif(LocalDate.now());
        empruntRepository.save(emprunt);

        // Augmenter le nombre d'exemplaires disponibles
        Livre livre = emprunt.getLivre();
        livre.setNombreExemplaires(livre.getNombreExemplaires() + 1);
        livreRepository.save(livre);

        return new EmpruntDTO(emprunt);
    }

    // Historique des emprunts
    public List<EmpruntDTO> getHistoriqueEmpruntsByUser(Long userId) {
        List<Emprunt> emprunts = empruntRepository.findByUtilisateurId(userId);
        return emprunts.stream().map(EmpruntDTO::new).collect(Collectors.toList());
    }


    public List<EmpruntDTO> getEmpruntsEnCours() {
        List<Emprunt> emprunts = empruntRepository.findByDateRetourEffectifIsNull();
        return emprunts.stream().map(EmpruntDTO::new).collect(Collectors.toList());
    }

    public List<EmpruntDTO> getHistoriqueEmprunts() {
        List<Emprunt> emprunts = empruntRepository.findAll();
        return emprunts.stream().map(EmpruntDTO::new).collect(Collectors.toList());
    }

    public List<EmpruntDTO> getEmpruntsEnCoursParUtilisateur(Long utilisateurId) {
        return empruntRepository.findByUtilisateurId(utilisateurId)
                .stream()
                .map(EmpruntMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmpruntDTO> getHistoriqueEmpruntsParUtilisateur(Long utilisateurId) {
        return empruntRepository.findByUtilisateurId(utilisateurId)
                .stream()
                .map(EmpruntMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retourne la liste des emprunts en retard pour un utilisateur donné.
    public List<EmpruntDTO> getEmpruntsEnRetard(Long utilisateurId) {
        LocalDate today = LocalDate.now();
        List<Emprunt> empruntsEnRetard = empruntRepository.findByUtilisateurIdAndDateRetourPrevuBeforeAndDateRetourEffectifIsNull(utilisateurId, today);

        return empruntsEnRetard.stream()
                .map(EmpruntDTO::new)
                .collect(Collectors.toList());
    }

    public List<EmpruntDTO> getEmpruntsEnRetardParUtilisateurPourAdmin(Long utilisateurId) {
        LocalDate today = LocalDate.now();
        List<Emprunt> empruntsEnRetard = empruntRepository.findByUtilisateurIdAndDateRetourPrevuBeforeAndDateRetourEffectifIsNull(utilisateurId, today);

        if (empruntsEnRetard.isEmpty()) {
            throw new NotFoundException("Aucun emprunt en retard pour cet utilisateur.");
        }

        return empruntsEnRetard.stream()
                .map(EmpruntDTO::new)
                .collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmpruntDTO> getEmpruntsEnRetardPourAdmin() {
        LocalDate today = LocalDate.now();
        List<Emprunt> empruntsEnRetard = empruntRepository.findByDateRetourPrevuBeforeAndDateRetourEffectifIsNull(today);

        if (empruntsEnRetard.isEmpty()) {
            throw new NotFoundException("Aucun emprunt en retard.");
        }

        return empruntsEnRetard.stream()
                .map(EmpruntDTO::new)
                .collect(Collectors.toList());
    }

}
