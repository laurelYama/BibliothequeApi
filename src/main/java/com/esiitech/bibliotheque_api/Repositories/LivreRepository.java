package com.esiitech.bibliotheque_api.Repositories;

import com.esiitech.bibliotheque_api.Entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
}
