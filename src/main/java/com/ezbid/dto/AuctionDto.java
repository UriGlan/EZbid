package com.ezbid.dto;


public class AuctionDto {
    private Long auction_id;
    private String username;
    private String title;
    private String subtitle;
    private String description;
    private CurrBidDto currentBid;
    private double startingBid;
    private boolean active;

    public AuctionDto() {
    }

    public AuctionDto(Long auction_id, String username, String title,String subtitle, String description, CurrBidDto currentBid,Double startingBid, boolean active) {
        this.auction_id = auction_id;
        this.username = username;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.currentBid = currentBid;
        this.startingBid = startingBid;
        this.active = active;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}


