package com.esiitech.bibliotheque_api.Exception;


public class EmpruntNonAutoriseException extends RuntimeException {
    public EmpruntNonAutoriseException(String message) {
        super(message);
    }
}

