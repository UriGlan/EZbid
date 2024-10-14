package com.ezbid.service;

import com.ezbid.Utils.DtoUtils;
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

    // Constructor injection
    public AuctionService(AuctionRepository auctionRepository, EntityManager entityManager, UserRepository userRepository) {
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
    }

    // Get all auctions and convert them to DTOs
    public List<AuctionDto> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        return auctions.stream()
                .map(DtoUtils::convertToDto)
                .collect(Collectors.toList());
    }

    // Create an auction from a DTO
    public AuctionDto createAuction(AuctionDto auctionDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Auction auction = DtoUtils.convertToEntity(auctionDto);
        auction.setUser(user);
        auctionRepository.save(auction);
        return DtoUtils.convertToDto(auction);
    }
    // Delete auction by ID
    public void deleteAuction(Long auctionId) {
        auctionRepository.deleteById(auctionId);
    }

    // Get auction by ID and convert to DTO
    public AuctionDto getAuctionById(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
        return DtoUtils.convertToDto(auction);
    }

    // Convert Auction entity to AuctionDto


    public List<AuctionDto> getAllActiveAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        return auctions.stream()
                .map(DtoUtils::convertToDto)
                .collect(Collectors.toList());
    }

    public AuctionDto endAuction(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
        auction.setActive(false);
        auctionRepository.save(auction);
        return DtoUtils.convertToDto(auction);
    }
}
