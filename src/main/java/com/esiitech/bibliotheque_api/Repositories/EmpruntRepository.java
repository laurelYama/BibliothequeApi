package com.esiitech.bibliotheque_api.Repositories;

import com.esiitech.bibliotheque_api.Entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    // Trouver les emprunts en cours d'un utilisateur
    List<Emprunt> findByUtilisateurIdAndDateRetourEffectifIsNull(Long utilisateurId);

    // Vérifier si un utilisateur a déjà emprunté un livre sans l'avoir rendu
    boolean existsByUtilisateurIdAndLivreIdAndDateRetourEffectifIsNull(Long utilisateurId, Long livreId);

    // Trouver tous les emprunts d'un utilisateur
    List<Emprunt> findByUtilisateurId(Long utilisateurId);

    // Trouver tous les emprunts en cours
    List<Emprunt> findByDateRetourEffectifIsNull();

    // Trouver les emprunts en retard (dateRetourPrevu dépassée et non retourné)
    List<Emprunt> findByDateRetourPrevuBeforeAndDateRetourEffectifIsNull(LocalDate date);

    // Trouver les emprunts en retard d'un utilisateur spécifique
    List<Emprunt> findByUtilisateurIdAndDateRetourPrevuBeforeAndDateRetourEffectifIsNull(Long utilisateurId, LocalDate date);


}
