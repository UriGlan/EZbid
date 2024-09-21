package com.ezbid.model;

import com.ezbid.model.Auction;
import com.ezbid.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double bidAmount;
    private LocalDateTime bidTime;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User bidder;

    public Bid() {
    }

    public Bid(Long id, Double bidAmount, LocalDateTime bidTime, Auction auction, User bidder) {
        this.id = id;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
        this.auction = auction;
        this.bidder = bidder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }
}