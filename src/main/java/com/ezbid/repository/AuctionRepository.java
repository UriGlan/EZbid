package com.ezbid.repository;

import com.ezbid.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;


// This interface extends JpaRepository to perform CRUD operations on the Auction entity

public interface AuctionRepository extends JpaRepository<Auction, Long> {

}
