package com.ezbid.controller;

import com.ezbid.dto.AuctionDto;
import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Auction;
import com.ezbid.service.AuctionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("/allAuctions")
    public ResponseEntity<List<AuctionDto>> getAllAuctions() {
        System.out.println("Controller method reached: Getting all auctions");
        List<AuctionDto> auctions = auctionService.getAllAuctions();
        if (auctions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auctions);
    }


    // This method creates a new auction
    @PostMapping(value = "/new", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<AuctionDto> createAuction(
            @RequestPart("auctionDto") AuctionDto auctionDto,  // Deserialized automatically by Spring
            @RequestPart(value = "img", required = false) MultipartFile img,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        AuctionDto createdAuction = auctionService.createAuction(auctionDto, username, img);
        return ResponseEntity.ok(createdAuction);
    }



    // This method deletes an auction
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuction(id);
        return ResponseEntity.noContent().build();
    }

    // This method returns an auction by its ID
    @GetMapping("/{id}")
    public ResponseEntity<AuctionDto> getAuctionById(@PathVariable Long id) {
        AuctionDto auctionDTO = auctionService.getAuctionById(id);
        return ResponseEntity.ok(auctionDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuctionDto>> getAllActiveAuctions() {
        List<AuctionDto> auctions = auctionService.getAllAuctions();
        if (auctions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auctions);
    }

    @GetMapping("/endAuction")
    public ResponseEntity<AuctionDto> endAuction(@PathVariable Long id) {
        AuctionDto auctionDTO = auctionService.endAuction(id);
        return ResponseEntity.ok(auctionDTO);
    }


}