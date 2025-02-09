package com.esiitech.bibliotheque_api.Mappers;

import com.esiitech.bibliotheque_api.DTO.LivreDTO;
import com.esiitech.bibliotheque_api.Entities.Livre;

public class LivreMapper {

    // Conversion d'une entité Livre en DTO LivreDTO
    public static LivreDTO toDTO(Livre livre) {
        if (livre == null) {
            return null;  // Si l'entité Livre est null, retourner null
        }
        LivreDTO dto = new LivreDTO();
        dto.setId(livre.getId());
        dto.setTitre(livre.getTitre());
        dto.setAuteur(livre.getAuteur());
        dto.setIsbn(livre.getIsbn());
        dto.setNombreExemplaires(livre.getNombreExemplaires());
        return dto;
    }

    // Conversion d'un DTO LivreDTO en entité Livre
    public static Livre toEntity(LivreDTO dto) {
        if (dto == null) {
            return null;  // Si le DTO est null, retourner null
        }
        Livre livre = new Livre();
        livre.setId(dto.getId());
        livre.setTitre(dto.getTitre());
        livre.setAuteur(dto.getAuteur());
        livre.setIsbn(dto.getIsbn());
        livre.setNombreExemplaires(dto.getNombreExemplaires());
        return livre;
    }



}
