package com.ezbid.service;

import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Bid;
import com.ezbid.repository.BidRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public Bid createBid(Bid bid) {
        return bidRepository.save(bid);
    }

    public Bid updateBid(Bid bid) {
        return bidRepository.save(bid);
    }

    public void deleteBid(Long id) {
        bidRepository.deleteById(id);
    }
    public List<Bid> getBidsByAuctionId(Long auctionId, int page, int size) {
        return bidRepository.findByAuctionId(auctionId, PageRequest.of(page, size));
    }


    public Bid getBidById(Long id) {
        return bidRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bid not found"));
    }

    // Additional methods for updating and deleting bids
}