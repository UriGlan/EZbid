package com.ezbid.controller;

import com.ezbid.dto.BidDto;
import com.ezbid.dto.PlaceBidDto;
import com.ezbid.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller for handling bids
@RestController
@RequestMapping("/api/bids")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    // Place a bid
    @PostMapping("/place")
    public ResponseEntity<Void> placeBid(@RequestBody PlaceBidDto bidDto, @AuthenticationPrincipal UserDetails userDetails)  {
        bidService.placeBid(bidDto, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    // Get all bids
    @GetMapping("/mybids")
    public ResponseEntity<List<BidDto>> getMyBids(@AuthenticationPrincipal UserDetails userDetails) {
        try{
        List<BidDto> myBids = bidService.getMyBids(userDetails.getUsername());
        return ResponseEntity.ok(myBids);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
