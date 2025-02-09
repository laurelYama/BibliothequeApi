package com.esiitech.bibliotheque_api.Exception;


public class DatabaseConstraintException extends RuntimeException {
    public DatabaseConstraintException(String message) {
        super(message);
    }
}
