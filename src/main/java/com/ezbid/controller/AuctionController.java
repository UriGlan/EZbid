package com.ezbid.controller;

import com.ezbid.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuctionController {

    //@GetMapping("/auctions")
    public List<Auction> getAllAuctions() {
        // Logic to retrieve all auctions
        return null;
    }

    @PostMapping("/auctions")
    public Auction createAuction(@RequestBody Auction auction) {
        // Logic to create a new auction
        return null;
    }

    @GetMapping("/auctions/{id}")
    public Auction getAuctionById(@PathVariable Long id) {
        // Logic to retrieve a specific auction by id
        return null;
    }

    // Other endpoints like update, delete, etc.
}