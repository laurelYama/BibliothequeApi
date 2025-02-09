package com.esiitech.bibliotheque_api.Repositories;

import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    Optional<Utilisateur> findByEmail(String email);
    boolean existsByEmail(String email);
}
