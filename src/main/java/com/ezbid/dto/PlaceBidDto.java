package com.ezbid.dto;

public class PlaceBidDto {
    private Long auction_id;
    private Double bidAmount;

    public PlaceBidDto() {
    }

    public PlaceBidDto(Long auction_id, Double bidAmount) {
        this.auction_id = auction_id;
        this.bidAmount = bidAmount;
    }

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }
}
