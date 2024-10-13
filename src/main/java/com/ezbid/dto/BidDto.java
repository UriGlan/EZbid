package com.ezbid.dto;

import com.ezbid.model.Auction;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BidDto {
    private Long bid_id;
    private AuctionDto auction;
    private Double bidAmount;
    private String username;
    private LocalDateTime bidTime;

    public BidDto() {
    }

    public BidDto(AuctionDto auction, Double bidAmount, LocalDateTime bidTime, String username) {
        this.auction = auction;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
        this.username = username;
    }

    public AuctionDto getAuction() {
        return auction;
    }

    public void setAuction(AuctionDto auction) {
        this.auction = auction;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
