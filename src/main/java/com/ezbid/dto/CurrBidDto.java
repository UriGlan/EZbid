package com.ezbid.dto;

public class CurrBidDto {
    private Long bid_id;
    private double bidAmount;

    public CurrBidDto() {
    }

    public CurrBidDto(Long bid_id, double bidAmount) {
        this.bid_id = bid_id;
        this.bidAmount = bidAmount;
    }

    public Long getBid_id() {
        return bid_id;
    }

    public void setBid_id(Long bid_id) {
        this.bid_id = bid_id;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }
}
