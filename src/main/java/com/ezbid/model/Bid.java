package com.ezbid.model;

import com.ezbid.model.Auction;
import com.ezbid.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

// This class is the model for a bid

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid_id;
    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double bidAmount;
    private LocalDateTime bidTime;


    public Bid() {
    }

    public Bid(Long bid_id, Double bidAmount, LocalDateTime bidTime, Auction auction, User user) {
        this.bid_id = bid_id;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
        this.auction = auction;
        this.user = user;
    }

// Getters and setters

    public Long getId() {
        return bid_id;
    }
    public void setId(Long bid_id) {
        this.bid_id = bid_id;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}