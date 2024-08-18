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
    }
}

