package com.esiitech.bibliotheque_api.Mappers;

import com.esiitech.bibliotheque_api.DTO.EmpruntDTO;
import com.esiitech.bibliotheque_api.Entities.Emprunt;
import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import com.esiitech.bibliotheque_api.Entities.Livre;
import com.esiitech.bibliotheque_api.Services.LivreService;
import com.esiitech.bibliotheque_api.Services.UtilisateurService;
import org.mapstruct.Mapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@SpringBootApplication(scanBasePackages = "com.esiitech.bibliotheque_api")
public class EmpruntMapper {

    public static EmpruntDTO toDTO(Emprunt emprunt) {
        if (emprunt == null) {
            return null;
        }

        // récupération des données depuis l'objet emprunt
        EmpruntDTO dto = new EmpruntDTO(emprunt);
        dto.setId(emprunt.getId());
        dto.setUtilisateurId(emprunt.getUtilisateur().getId());
        dto.setLivreId(emprunt.getLivre().getId());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setDateRetourPrevu(emprunt.getDateRetourPrevu());
        dto.setDateRetourEffectif(emprunt.getDateRetourEffectif());

        return dto;
    }

    public static Emprunt toEntity(EmpruntDTO dto, UtilisateurService utilisateurService, LivreService livreService) {
        if (dto == null) {
            return null;
        }

        Utilisateur utilisateur = Optional.ofNullable(utilisateurService.getById(dto.getUtilisateurId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        Livre livre = livreService.getLivreById(dto.getLivreId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre non trouvé"));


        return new Emprunt(dto.getId(), utilisateur, livre, dto.getDateEmprunt(), dto.getDateRetourPrevu(), dto.getDateRetourEffectif());
    }

    public static List<EmpruntDTO> toDTOList(List<Emprunt> emprunts) {
        return emprunts == null ? List.of() : emprunts.stream().map(EmpruntMapper::toDTO).collect(Collectors.toList());
    }
}
