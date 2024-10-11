package com.ezbid.controller;

import com.ezbid.dto.AuctionDto;
import com.ezbid.model.Auction;
import com.ezbid.service.AuctionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is a REST controller that handles HTTP requests

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {
    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    // This method returns all auctions
    @GetMapping("/all")
    public ResponseEntity<List<AuctionDto>> getAllAuctions() {
        System.out.println("Controller method reached: Getting all auctions");
        List<AuctionDto> auctions = auctionService.getAllAuctions();
        if (auctions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auctions);
    }


    // This method creates a new auction
    @PostMapping("/new")
    public ResponseEntity<AuctionDto> createAuction(@RequestBody AuctionDto auctionDto, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();  // Extract the username from the token
        AuctionDto createdAuction = auctionService.createAuction(auctionDto, username);
        return ResponseEntity.ok(createdAuction);
    }

    // This method deletes an auction
    @DeleteMapping("/{id}")
    public void deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuction(id);
    }

    // This method returns an auction by its ID
    @GetMapping("/{id}")
    public ResponseEntity<AuctionDto> getAuctionById(@PathVariable Long id) {
        AuctionDto auctionDTO = auctionService.getAuctionById(id);
        return ResponseEntity.ok(auctionDTO);
    }

    // Add other endpoints like update, delete, etc.
}