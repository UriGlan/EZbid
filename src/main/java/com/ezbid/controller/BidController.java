package com.ezbid.controller;

import com.ezbid.dto.BidDto;
import com.ezbid.model.Bid;
import com.ezbid.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/place")
    public ResponseEntity<BidDto> placeBid(@RequestBody BidDto bidDto, @AuthenticationPrincipal UserDetails userDetails) {
        BidDto newBid = bidService.placeBid(bidDto, userDetails.getUsername());
        return ResponseEntity.ok(newBid);
    }
}