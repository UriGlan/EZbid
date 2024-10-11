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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bid_id")
    private Bid currentBid;
    private double startingBid;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int days = 7;
    private boolean active = true;



// Empty constructor
    public Auction() {
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusDays(days);
    }

    public Auction(User user, String title,Double startingBid, String description, int days) {
        this.user = user;
        this.title = title;
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Bid getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Bid currentBid) {
        this.currentBid = currentBid;
    }

    public void checkAuctionStatus() {
        this.active = LocalDateTime.now().isBefore(this.endTime);
    }
}