package com.ezbid.service;

import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Auction;
import com.ezbid.repository.AuctionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;
    private EntityManager entityManager;


    public List<Auction> getAllAuctions() {

        return auctionRepository.findAll();
    }

    public Auction createAuction(Auction auction) {

        return auctionRepository.save(auction);
    }

    public Auction updateAuction(Auction auction) {
        return auctionRepository.save(auction);
    }

    public void deleteAuction(Long id) {
        auctionRepository.deleteById(id);
    }

    public List<Auction> getAuctionsByUserId(Long userId) {
        return auctionRepository.findByUserId(userId);
    }

    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
    }


    // Additional methods for updating and deleting auctions
}

