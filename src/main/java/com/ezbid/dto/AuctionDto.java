package com.ezbid.dto;

import com.ezbid.model.Bid;

import java.time.LocalDateTime;

public class AuctionDto {
    private Long auction_id;
    private String username;
    private String title;
    private String description;
    private BidDto currentBid;
    private double startingBid;

    public AuctionDto() {
    }

    public AuctionDto(Long auction_id, String username, String title, String description, BidDto currentBid,Double startingBid) {
        this.auction_id = auction_id;
        this.username = username;
        this.title = title;
        this.description = description;
        this.currentBid = currentBid;
        this.startingBid = startingBid;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BidDto getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(BidDto currentBid) {
        this.currentBid = currentBid;
    }

    public double getStartingBid() {
        return startingBid;
    }
    public void setStartingBid(double startingBid) {
        this.startingBid = startingBid;
    }
}


