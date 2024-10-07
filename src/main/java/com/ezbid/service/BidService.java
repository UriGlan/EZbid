package com.ezbid.service;

import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Bid;
import com.ezbid.repository.BidRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

// This class is a service that handles logics for bids

@Service
@Transactional(rollbackOn = Exception.class)
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    // This method returns all bids
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }


    //  This method creates a bid
    public Bid createBid(Bid bid) {
        return bidRepository.save(bid);
    }

    //  This method updates a bid
    public Bid updateBid(Bid bid) {
        return bidRepository.save(bid);
    }

    // This method deletes a bid
    public void deleteBid(Long id) {
        bidRepository.deleteById(id);
    }


    // This method returns a bid by id
    public Bid getBidById(Long id) {
        return bidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bid not found"));
    }

    // Additional methods for updating and deleting bids
}