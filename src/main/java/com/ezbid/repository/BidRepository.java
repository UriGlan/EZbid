package com.ezbid.repository;

import com.ezbid.model.Auction;
import com.ezbid.model.Bid;
import com.ezbid.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
// This interface extends JpaRepository to perform CRUD operations on the Bid entity


public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByUser(User user);

    List<Bid> findByUserAndAuction(User user, Auction auction);

    void deleteByAuction(Auction auction);
}
