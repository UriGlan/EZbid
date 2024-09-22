package com.ezbid.controller;

import com.ezbid.model.Auction;
import com.ezbid.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    @GetMapping("/auctions")
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    @PostMapping("/auctions/new")
    public Auction createAuction(@RequestBody Auction auction) {
        return auctionService.createAuction(auction);
    }

    @GetMapping("/auctions/{id}")
    public Auction getAuctionById(@PathVariable Long id) {
        return auctionService.getAuctionById(id);
    }

    // Other endpoints like update, delete, etc.
}