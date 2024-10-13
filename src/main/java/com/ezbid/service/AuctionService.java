package com.ezbid.service;

import com.ezbid.dto.AuctionDto;
import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Auction;
import com.ezbid.model.User;
import com.ezbid.repository.AuctionRepository;
import com.ezbid.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final BidService bidService;

    // Constructor injection
    public AuctionService(AuctionRepository auctionRepository, EntityManager entityManager, UserRepository userRepository, BidService bidService) {
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
        this.bidService = bidService;
    }

    // Get all auctions and convert them to DTOs
    public List<AuctionDto> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        return auctions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Create an auction from a DTO
    public AuctionDto createAuction(AuctionDto auctionDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Auction auction = convertToEntity(auctionDto, username);
        auction.setUser(user);
        auctionRepository.save(auction);
        return convertToDto(auction);
    }
    // Delete auction by ID
    public void deleteAuction(Long auctionId) {
        auctionRepository.deleteById(auctionId);
    }

    // Get auction by ID and convert to DTO
    public AuctionDto getAuctionById(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
        return convertToDto(auction);
    }

    // Convert Auction entity to AuctionDto
    public AuctionDto convertToDto(Auction auction) {
        AuctionDto dto = new AuctionDto();
        dto.setAuction_id(auction.getAuction_id());
        dto.setUsername(auction.getUser().getUsername());
        dto.setTitle(auction.getTitle());
        dto.setDescription(auction.getDescription());
        dto.setStartingBid(auction.getStartingBid());
        dto.setCurrentBid(bidService.convertToDto(auction.getCurrentBid()));
        return dto;
    }

    // Convert AuctionDto to Auction entity
    private Auction convertToEntity(AuctionDto dto, String username) {
        Auction auction = new Auction();
        auction.setTitle(dto.getTitle());
        auction.setDescription(dto.getDescription());
        auction.setStartingBid(dto.getStartingBid());
        auction.setCurrentBid(bidService.convertToEntity(dto.getCurrentBid()));
        return auction;
    }

    public List<AuctionDto> getAllActiveAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        return auctions.stream()
                .filter(auction -> auction.getEndTime().isAfter(java.time.LocalDateTime.now()))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
