package com.ezbid.repository;

import com.ezbid.model.Bid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByAuctionId(Long auctionId, PageRequest of);
}
