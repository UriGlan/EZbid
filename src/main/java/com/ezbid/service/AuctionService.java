package com.ezbid.service;

import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Auction;
import com.ezbid.repository.AuctionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AuctionService {

    private final AuctionRepository auctionRepository;

    // Constructor injection for better practices
    public AuctionService(AuctionRepository auctionRepository, EntityManager entityManager) {
        this.auctionRepository = auctionRepository;
    }

    // This method returns all auctions
    public List<Auction> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        System.out.println("Auctions fetched from DB: " + auctions);
        return auctions;
    }

    // This method creates an auction
    public Auction createAuction(Auction auction) {
        return auctionRepository.save(auction);
    }


    // This method deletes an auction
    public void deleteAuction(Long auctionId) {
        auctionRepository.deleteById(auctionId);
    }

    // This method returns an auction by id
    public Auction getAuctionById(Long auctionId) {
        return auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
    }
    // Optional method tho add a picture:
    // Method to upload image
    // Method to store file locally (Consider security implications when storing user-uploaded files)

}
