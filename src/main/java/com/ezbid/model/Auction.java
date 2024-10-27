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

    @JoinColumn(name = "category_id")
    private Long categoryId;
    private double startingBid;
    private int bidsNumber;
    private String title;
    private String subtitle;
    @Lob
    @Column(length = 1000)
    private String description;
    private int daysLeft = 7;
    private LocalDateTime startTime= LocalDateTime.now();
    private LocalDateTime endTime = LocalDateTime.now().plusDays(daysLeft);
    private boolean active = true;
    private String imageUrl;


    // Empty constructor
    public Auction() {

    }

    public Auction(User user, String title,String subtitle, Double startingBid, String description, Long categoryId, int daysLeft, String imageUrl) {
        this.user = user;
        this.title = title;
        this.subtitle = subtitle;
        this.startingBid = startingBid;
        this.bidsNumber = 0;
        this.description = description;
        this.categoryId = categoryId;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusDays(daysLeft);
        this.active = true;
        this.imageUrl = imageUrl;
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
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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
        if (endTime != null) {
            this.endTime = endTime;
        } else {
            this.endTime = LocalDateTime.now().plusDays(daysLeft);
        }
    }

    public void setStartTime(LocalDateTime startTime) {
        if (startTime != null) {
            this.startTime = startTime;
        } else {
            this.startTime = LocalDateTime.now();
        }

    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
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

    public int getBidsNumber() {
        return bidsNumber;
    }

    public void setBidsNumber(int bidsNumber) {
        this.bidsNumber = bidsNumber;
    }

    public void checkAuctionStatus() {
        this.active = LocalDateTime.now().isBefore(this.endTime);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}