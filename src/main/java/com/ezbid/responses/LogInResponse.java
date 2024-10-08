package com.ezbid.responses;

public class LogInResponse {
    private String token;
    private String expiresIn;

    public LogInResponse(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

}
