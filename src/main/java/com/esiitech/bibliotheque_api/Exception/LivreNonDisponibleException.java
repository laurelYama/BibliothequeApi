package com.esiitech.bibliotheque_api.Exception;

public class LivreNonDisponibleException extends RuntimeException {

    // Constructeur avec message
    public LivreNonDisponibleException(String message) {
        super(message);
    }

    // Constructeur sans message
    public LivreNonDisponibleException() {
        super("Le livre n'est pas disponible.");
    }
}
