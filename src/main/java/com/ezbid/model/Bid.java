package com.ezbid.model;

import com.ezbid.model.Auction;
import com.ezbid.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double bidAmount;
    private LocalDateTime bidTime;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User bidder;

    public Bid() {
    }

    public Bid(Long id, Double bidAmount, LocalDateTime bidTime, Auction auction, User bidder) {
        this.id = id;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
        this.auction = auction;
        this.bidder = bidder;
    }

}
