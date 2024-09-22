package com.ezbid.service;

import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Auction;
import com.ezbid.repository.AuctionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Struct;
import java.util.List;

import static com.ezbid.constant.Constant.PHOTO_DIRECTORY;

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


    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
    }

    // Method to upload image
    public String uploadImage(Long id, MultipartFile file) {
        Auction auction = getAuctionById(id);
        String filePath = storeFile(file); // Store the file
        auction.setImage(filePath);
        auctionRepository.save(auction);
        return filePath; // Return the file path in string format
    }


    // Method to store file locally (need to change fi the file is stored on a cloud storage service)
    public String storeFile(MultipartFile file) {
        long maxFileSizeInBytes = 5 * 1024 * 1024; // Set to 5MB.

        if (file.getSize() > maxFileSizeInBytes) {
            throw new RuntimeException("File is too large. Maximum size is 5MB.");
        }

        try {
            Path path = Paths.get(PHOTO_DIRECTORY + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path);
            return path.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }





    // Additional methods for updating and deleting auctions
}

