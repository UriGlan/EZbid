package com.ezbid.controller;

import com.ezbid.model.Auction;
import com.ezbid.service.AuctionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is a REST controller that handles HTTP requests

@RestController
@RequestMapping("/api")
public class AuctionController {
    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    // This method returns all auctions
    @GetMapping("/auctions")
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    // This method creates a new auction
    @PostMapping("/auctions/new")
    public Auction createAuction(@RequestBody Auction auction) {
        return auctionService.createAuction(auction);
    }

    // This method deletes an auction
    @DeleteMapping("/auctions/{id}")
    public void deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuction(id);
    }

    // This method returns an auction by its ID
    @GetMapping("/auctions/{id}")
    public Auction getAuctionById(@PathVariable Long id) {
        return auctionService.getAuctionById(id);
    }

    // Add other endpoints like update, delete, etc.
}