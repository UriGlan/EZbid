package com.ezbid.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

// This class is the model for an auction
@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auction_id;

    private String itemName;
    private String description;
    private String image;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int days = 7;



// Empty constructor
    public Auction() {
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusDays(days);
    }

    public Auction(Long auction_id, String itemName, String description, int days) {
        this.auction_id = auction_id;
        this.itemName = itemName;
        this.description = description;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusDays(days);
    }
// Getters and setters
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

    public void setImage(String filePath) {
        this.image = filePath;
    }
}