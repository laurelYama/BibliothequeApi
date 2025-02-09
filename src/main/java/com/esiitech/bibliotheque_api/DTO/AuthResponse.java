package com.esiitech.bibliotheque_api.DTO;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private Long expirationTimestamp;
    private String expirationFormatted;


    public AuthResponse(String token, Long expirationTimestamp, String expirationFormatted) {
        this.token = token;
        this.expirationTimestamp = expirationTimestamp;
        this.expirationFormatted = expirationFormatted;
    }


    public AuthResponse() {
    }
}
