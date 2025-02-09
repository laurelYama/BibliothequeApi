package com.esiitech.bibliotheque_api.Repositories;

import com.esiitech.bibliotheque_api.Entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    List<Emprunt> findByUtilisateurIdAndDateRetourEffectifIsNull(Long utilisateurId);


    // Vérifier si un utilisateur a déjà emprunté un livre sans l'avoir rendu
    boolean existsByUtilisateurIdAndLivreIdAndDateRetourEffectifIsNull(Long utilisateurId, Long livreId);

    // Trouver tous les emprunts d'un utilisateur
    List<Emprunt> findByUtilisateurId(Long utilisateurId);

    List<Emprunt> findByDateRetourEffectifIsNull(); // Emprunts en cours


}