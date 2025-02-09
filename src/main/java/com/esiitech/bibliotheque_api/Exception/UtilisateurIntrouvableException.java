package com.esiitech.bibliotheque_api.Exception;

public class UtilisateurIntrouvableException extends RuntimeException {

    // Constructeur avec message
    public UtilisateurIntrouvableException(String message) {
        super(message);
    }

    // Constructeur sans message
    public UtilisateurIntrouvableException() {
        super("L'utilisateur demandé n'a pas été trouvé.");
    }
}
