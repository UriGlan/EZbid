package com.ezbid.responses;

public class LogInResponse {
    private String token;
    private Long expiresIn;

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

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

}
