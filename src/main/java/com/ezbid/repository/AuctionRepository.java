package com.ezbid.repository;

import com.ezbid.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// This interface extends JpaRepository to perform CRUD operations on the Auction entity

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    void delete(Auction auction);
    Optional<Auction> findById(Long auction_id);

}
