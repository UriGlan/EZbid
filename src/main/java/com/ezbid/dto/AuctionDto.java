package com.ezbid.dto;


import com.ezbid.model.Auction;

import java.time.LocalDateTime;

public class AuctionDto {
    private Long auction_id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String title;
    private String subtitle;
    private String description;
    private CurrBidDto currentBid;
    private double startingBid;
    private int bidsNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public AuctionDto() {
    }

    public AuctionDto(String username,String firstName, String lastName, String title,String subtitle, String description, CurrBidDto currentBid,Double startingBid,int bidsNumber, boolean active) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.currentBid = currentBid;
        this.startingBid = startingBid;
        this.bidsNumber = bidsNumber;
        if (active) {
            this.status = "Active";
        } else if (currentBid == null) {
            this.status = "Expired";
        } else {
            this.status = "Won";
        }

    }

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public CurrBidDto getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(CurrBidDto currentBid) {
        this.currentBid = currentBid;
    }

    public double getStartingBid() {
        return startingBid;
    }
    public void setStartingBid(double startingBid) {
        this.startingBid = startingBid;
    }

    public int getBidsNumber() {
        return bidsNumber;
    }

    public void setBidsNumber(int bidsNumber) {
        this.bidsNumber = bidsNumber;
    }

    public boolean isActive() {
        return status.equals("Active");
    }

    public void setActive(boolean active) {

        if (active) {
            this.status = "Active";
        } else if (currentBid == null) {
            this.status = "Expired";
        } else {
            this.status = "Won";
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


