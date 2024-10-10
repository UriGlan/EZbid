package com.ezbid.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

// This class is the model for an auction
@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auction_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String itemName;
    private double startingBid;
    private double currentBid;

    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int days = 7;



// Empty constructor
    public Auction() {
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusDays(days);
    }

    public Auction(Long auction_id,User user, String itemName,Double startingBid, String description, int days) {
        this.auction_id = auction_id;
        this.user = user;
        this.itemName = itemName;
        this.startingBid = startingBid;
        this.description = description;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusDays(days);
    }
// Getters and setters
    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }
    public Long getAuction_id() {
        return auction_id;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public double getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(double startingBid) {
        this.startingBid = startingBid;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }
}