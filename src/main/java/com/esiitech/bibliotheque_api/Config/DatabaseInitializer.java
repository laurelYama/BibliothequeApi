package com.esiitech.bibliotheque_api.Config;

import com.esiitech.bibliotheque_api.Entities.Utilisateur;
import com.esiitech.bibliotheque_api.Enum.Role;
import com.esiitech.bibliotheque_api.Repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner initAdmin(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Optional<Utilisateur> adminExist = utilisateurRepository.findByEmail("admin@bibliotheque.com");

            if (adminExist.isEmpty()) {
                Utilisateur admin = new Utilisateur();
                admin.setNom("Super Admin");
                admin.setEmail("admin@bibliotheque.com");
                admin.setMotDePasse(passwordEncoder.encode("Admin1234"));
                admin.setRole(Role.valueOf("ADMIN"));

                utilisateurRepository.save(admin);
                System.out.println("Compte ADMIN créé : admin@bibliotheque.com / Admin1234");
            } else {
                System.out.println("Un compte ADMIN existe déjà.");
            }
        };
    }
}

