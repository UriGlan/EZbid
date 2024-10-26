package com.ezbid.Utils;

import com.ezbid.dto.AuctionDto;
import com.ezbid.dto.CurrBidDto;
import com.ezbid.model.Auction;
import com.ezbid.model.Bid;

public class DtoUtils {

    public static CurrBidDto convertToCurrBidDto(Bid bid) {
        if (bid == null) {
            return null;
        }
        CurrBidDto dto = new CurrBidDto();
        dto.setBid_id(bid.getId());
        dto.setBidAmount(bid.getBidAmount());
        return dto;
    }

    public static Bid convertToEntity(CurrBidDto dto) {
        if (dto == null) {
            return null;
        }
        Bid bid = new Bid();
        bid.setId(dto.getBid_id());
        bid.setBidAmount(dto.getBidAmount());
        return bid;
    }

    public static AuctionDto convertToDto(Auction auction) {
        AuctionDto dto = new AuctionDto();
        dto.setAuction_id(auction.getAuction_id());
        dto.setUsername(auction.getUser().getUsername());
        dto.setEmail(auction.getUser().getEmail());
        dto.setFirstName(auction.getUser().getFirstName());
        dto.setLastName(auction.getUser().getLastName());
        dto.setTitle(auction.getTitle());
        dto.setSubtitle(auction.getSubtitle());
        dto.setDescription(auction.getDescription());
        dto.setCategoryId(auction.getCategoryId());
        dto.setStartingBid(auction.getStartingBid());
        dto.setCurrentBid(convertToCurrBidDto(auction.getCurrentBid()));
        dto.setBidsNumber(auction.getBidsNumber());
        dto.setDaysLeft(auction.getDaysLeft());
        dto.setStartTime(auction.getStartTime());
        dto.setEndTime(auction.getEndTime());
        auction.checkAuctionStatus();
        dto.setActive(auction.isActive());
        dto.setImageUrl(auction.getImageUrl());
        return dto;
    }

    // Convert AuctionDto to Auction entity
    public static Auction convertToEntity(AuctionDto dto) {
        Auction auction = new Auction();
        auction.setTitle(dto.getTitle());
        auction.setSubtitle(dto.getSubtitle());
        auction.setDescription(dto.getDescription());
        auction.setCategoryId(dto.getCategoryId());
        auction.setStartingBid(dto.getStartingBid());
        auction.setCurrentBid(convertToEntity(dto.getCurrentBid()));
        auction.setBidsNumber(dto.getBidsNumber());
        auction.setDaysLeft(dto.getDaysLeft());
        auction.setStartTime(dto.getStartTime());
        auction.setEndTime(dto.getEndTime());
        auction.setActive(dto.isActive());
        auction.setImageUrl(dto.getImageUrl());
        return auction;
    }
}
