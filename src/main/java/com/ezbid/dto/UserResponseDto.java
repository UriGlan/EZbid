package com.ezbid.dto;

import com.ezbid.model.Auction;
import com.ezbid.model.Bid;

import java.util.List;

public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private List<Auction> auctions;
    private List<Bid> bids;

    public UserResponseDto(Long id, String username, String email, List<Auction> auctions, List<Bid> bids) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.auctions = auctions;
        this.bids = bids;
    }

// Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
