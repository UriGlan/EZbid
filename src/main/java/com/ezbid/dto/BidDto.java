package com.ezbid.dto;

import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BidDto {
    private Long auction_id;
    private Double bidAmount;
    private String username;
    private LocalDateTime bidTime;

    public BidDto() {
    }

    public BidDto(Long auction_id, Double bidAmount, LocalDateTime bidTime, String username) {
        this.auction_id = auction_id;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
        this.username = username;
    }

    public Long getAuctionId() {
        return auction_id;
    }

    public void setAuctionId(Long auction_id) {
        this.auction_id = auction_id;
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
