package com.esiitech.bibliotheque_api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Méthode générique pour construire la réponse d'erreur
     */
    private ResponseEntity<Map<String, Object>> buildResponseEntity(HttpStatus status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    /**
     * Exception pour une requête invalide
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(BadRequestException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * Exception pour un élément non trouvé (utilisateur, livre, etc.)
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Exception pour un livre non disponible
     */
    @ExceptionHandler(LivreNonDisponibleException.class)
    public ResponseEntity<Map<String, Object>> handleLivreNonDisponibleException(LivreNonDisponibleException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Exception lorsqu'un utilisateur est introuvable
     */
    @ExceptionHandler(UtilisateurIntrouvableException.class)
    public ResponseEntity<Map<String, Object>> handleUtilisateurIntrouvableException(UtilisateurIntrouvableException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Exception pour une tentative d'emprunt non autorisée
     */
    @ExceptionHandler(EmpruntNonAutoriseException.class)
    public ResponseEntity<Map<String, Object>> handleEmpruntNonAutoriseException(EmpruntNonAutoriseException ex) {
        return buildResponseEntity(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    /**
     * Exception pour une violation des contraintes de la base de données
     */
    @ExceptionHandler(DatabaseConstraintException.class)
    public ResponseEntity<Map<String, String>> handleDatabaseConstraintException(DatabaseConstraintException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("code", "409");  // Code HTTP 409 : Conflit
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    /**
     * Exception pour une erreur liée aux données fournies
     */
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidDataException(InvalidDataException ex) {
        return buildResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    /**
     * Exception générique pour les erreurs non gérées
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur est survenue. Veuillez réessayer plus tard.");
    }
}
