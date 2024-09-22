package com.ezbid.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String description;
    private String image;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User seller;

    public Auction() {
    }

    public Auction(Long id, String itemName, String description, LocalDateTime startTime, LocalDateTime endTime, User seller) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seller = seller;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void setImage(String fileName) {
        this.image = fileName;
    }

    public String getImage() {
        return image;
    }


}