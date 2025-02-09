package com.esiitech.bibliotheque_api.Services;

import com.esiitech.bibliotheque_api.DTO.LivreDTO;
import com.esiitech.bibliotheque_api.Entities.Livre;
import com.esiitech.bibliotheque_api.Mappers.LivreMapper;
import com.esiitech.bibliotheque_api.Repositories.LivreRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.esiitech.bibliotheque_api.Mappers.LivreMapper.toDTO;

@Service
public class LivreService {



    public Optional<Livre> getLivreById(@NotNull Long id) {
        return livreRepository.findById(id);
    }

    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }


    public List<LivreDTO> getAllLivres() {
        return livreRepository.findAll().stream()
                .map(LivreMapper::toDTO)
                .collect(Collectors.toList());
    }


    public LivreDTO createLivre(LivreDTO livreDTO) {
        Livre livre = LivreMapper.toEntity(livreDTO);
        livre = livreRepository.save(livre);
        return toDTO(livre);
    }

    public LivreDTO modifierLivre(Long id, LivreDTO livreDTO) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        livre.setTitre(livreDTO.getTitre());
        livre.setAuteur(livreDTO.getAuteur());
        livre.setIsbn(livreDTO.getIsbn());
        livre.setNombreExemplaires(livreDTO.getNombreExemplaires());

        Livre updatedLivre = livreRepository.save(livre);
        return toDTO(updatedLivre);
    }
    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }
}
