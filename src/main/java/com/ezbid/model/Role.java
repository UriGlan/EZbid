package com.ezbid.model;

// This enum class is used to define the roles of the users - Admin or User
public enum Role {
    ADMIN,
    USER;

    public String getDisplayName() {
        switch (this) {
            case ADMIN:
                return "Administrator";
            case USER:
                return "Bidder";
            default:
                throw new IllegalArgumentException("Unknown role: " + this);
        }
    }
}