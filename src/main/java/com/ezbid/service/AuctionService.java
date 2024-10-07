package com.ezbid.service;

import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Auction;
import com.ezbid.repository.AuctionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static com.ezbid.constant.Constant.PHOTO_DIRECTORY;

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
        return auctionRepository.findAll();
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

    // Method to upload image
    public String uploadImage(Long auctionId, MultipartFile file) {
        Auction auction = getAuctionById(auctionId);
        String filePath = storeFile(file); // Store the file
        auction.setImage(filePath);
        auctionRepository.save(auction);
        return filePath; // Return the file path in string format
    }

    // Method to store file locally (Consider security implications when storing user-uploaded files)
    public String storeFile(MultipartFile file) {
        long maxFileSizeInBytes = 5 * 1024 * 1024; // Set to 5MB.

        if (file.getSize() > maxFileSizeInBytes) {
            throw new RuntimeException("File is too large. Maximum size is 5MB.");
        }

        try {
            Path path = Paths.get(PHOTO_DIRECTORY + file.getOriginalFilename());

            // Security consideration: Check if the file type is allowed
            if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
                throw new RuntimeException("Only image files are allowed.");
            }

            Files.copy(file.getInputStream(), path);
            return path.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
