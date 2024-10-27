package com.ezbid.service;

import com.ezbid.Utils.DtoUtils;
import com.ezbid.dto.PlaceBidDto;
import com.ezbid.model.Auction;
import com.ezbid.dto.BidDto;
import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Bid;
import com.ezbid.model.User;
import com.ezbid.repository.AuctionRepository;
import com.ezbid.repository.BidRepository;
import com.ezbid.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// This class is a service that handles logics for bids

@Service
@Transactional(rollbackOn = Exception.class)
public class BidService {

    private final BidRepository bidRepository;
    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;

    public BidService(BidRepository bidRepository, UserRepository userRepository, AuctionRepository auctionRepository) {
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
        this.auctionRepository = auctionRepository;
    }

    // Place new bid
    public void placeBid(PlaceBidDto bidDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Auction auction = auctionRepository.findById(bidDto.getAuction_id())
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
        if(auction.getCurrentBid() != null && bidDto.getBidAmount() <= auction.getCurrentBid().getBidAmount()) {
            throw new IllegalArgumentException("Bid amount must be greater than current bid amount");
        }
        // remove the last bid with the same user_id and auction_id
        List<Bid> lastBid = bidRepository.findByUserAndAuction(user, auction);
        if (!lastBid.isEmpty()) {
            bidRepository.delete(lastBid.get(0));
        }
        auction.setBidsNumber(auction.getBidsNumber() + 1);
        // Create a new bid - if the bid amount is greater than the current bid amount
        Bid bid = new Bid();
        bid.setAuction(auction);
        bid.setUser(user);
        bid.setBidAmount(bidDto.getBidAmount());
        bid.setBidTime(LocalDateTime.now());
        Bid savedBid = bidRepository.save(bid);
        auction.setCurrentBid(savedBid);
        auctionRepository.save(auction);
    }

    public BidDto convertToDto(Bid bid) {
        if (bid == null) {
            return null;
        }
        BidDto dto = new BidDto();
        dto.setAuction(DtoUtils.convertToDto(bid.getAuction()));
        dto.setBidAmount(bid.getBidAmount());
        dto.setBidTime(bid.getBidTime());
        dto.setUsername(bid.getUser().getUsername());
        return dto;
    }

    public Bid convertToEntity(BidDto currentBid) {
        Bid bid = new Bid();
        bid.setAuction(auctionRepository.findById(currentBid.getAuction().getAuction_id())
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found")));
        bid.setBidAmount(currentBid.getBidAmount());
        bid.setBidTime(currentBid.getBidTime());
        bid.setUser(userRepository.findByUsername(currentBid.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));
        return bid;
    }



    public List<BidDto> getMyBids(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Bid> myBids = bidRepository.findByUser(user);
        return myBids.stream().map(this::convertToDto).toList();
    }
}