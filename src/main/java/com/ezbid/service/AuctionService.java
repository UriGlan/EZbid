package com.ezbid.service;

import com.ezbid.Utils.DtoUtils;
import com.ezbid.dto.AuctionDto;
import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.Auction;
import com.ezbid.model.User;
import com.ezbid.repository.AuctionRepository;
import com.ezbid.repository.BidRepository;
import com.ezbid.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ezbid.constant.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

// This class is a service that handles logics for auctions
@Service
@Transactional(rollbackOn = Exception.class)
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final BidRepository bidRepository;

    // Constructor injection
    public AuctionService(AuctionRepository auctionRepository, UserRepository userRepository, BidRepository bidRepository) {
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
        this.bidRepository = bidRepository;
    }

    // Get all auctions and convert them to DTOs
    public List<AuctionDto> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        return auctions.stream()
                .map(DtoUtils::convertToDto)
                .collect(Collectors.toList());
    }

    // Create an auction from a DTO
    public AuctionDto createAuction(AuctionDto auctionDto,
                                    String username,
                                    MultipartFile img) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Auction auction = DtoUtils.convertToEntity(auctionDto);
        auction.setUser(user);
        auctionRepository.save(auction);
        if (img != null && !img.isEmpty()) {
            uploadImage(auction.getAuction_id(), img);
        }
        return DtoUtils.convertToDto(auction);
    }

    // Delete auction by ID
    @Transactional
    public void deleteAuction(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));

        // Delete bids associated with the auction
        bidRepository.deleteByAuction(auction);

        // Delete auction image if it exists
        if (auction.getImageUrl() != null) {
            deletePhotoFile(auction.getImageUrl());
        }

        // Delete the auction from the database
        auctionRepository.delete(auction);
    }

    // Method to delete the image file from the filesystem
    private void deletePhotoFile(String imageUrl) {
        try {
            // Extract the file name from the image URL
            String filename = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
            Path filePath = Paths.get(PHOTO_DIRECTORY, filename).toAbsolutePath().normalize();
            File file = filePath.toFile();

            // Delete the file if it exists
            if (file.exists()) {
                boolean deleted = file.delete();
                if (deleted) {
                    System.out.println("Image deleted successfully");
                } else {
                    System.err.println("Failed to delete image");
                }
            } else {
                System.out.println("Image file not found: " + filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred while deleting image file");
        }
    }

    // Get auction by ID and convert to DTO
    public AuctionDto getAuctionById(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
        return DtoUtils.convertToDto(auction);
    }

    // Get all active auctions and convert to DTO
    public List<AuctionDto> getAllActiveAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        return auctions.stream()
                .map(DtoUtils::convertToDto)
                .collect(Collectors.toList());
    }

    // End auction by setting it as inactive
    public AuctionDto endAuction(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
        auction.setActive(false);
        auctionRepository.save(auction);
        return DtoUtils.convertToDto(auction);
    }

    // Upload image to auction
    public String uploadImage(Long id, MultipartFile file) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found"));
        String imageUrl = uploadImage.apply(id.toString(), file);
        auction.setImageUrl(imageUrl);
        auctionRepository.save(auction);
        return imageUrl;
    }

    // Get the file extension of an image
    private final Function<String, String> fileExtension = (filename) -> {
        int index = filename.lastIndexOf('.');
        return index == -1 ? ".png" : filename.substring(index);
    };

    // Upload image to disk and return the URL
    private final BiFunction<String, MultipartFile, String> uploadImage = (id, file) -> {
        String filename = id + fileExtension.apply(file.getOriginalFilename());
        try {
            Path path = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists((path))){
                Files.createDirectories(path); // Create directories if they don't exist
            }
            Files.copy(file.getInputStream(), path.resolve(filename), REPLACE_EXISTING); // Save image to disk with auction ID as filename
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/uploads/").path(filename)
                    .toUriString();
        } catch (Exception e) {
            throw new RuntimeException("Could not upload image");
        }
    };
}
