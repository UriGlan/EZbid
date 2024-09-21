package com.ezbid.model;

public enum Role {
    ADMIN,
    BIDDER;

    public String getDisplayName() {
        switch (this) {
            case ADMIN:
                return "Administrator";
            case BIDDER:
                return "Bidder";
            default:
                throw new IllegalArgumentException("Unknown role: " + this);
        }
    }
}