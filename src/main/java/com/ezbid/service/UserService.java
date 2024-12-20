package com.ezbid.service;

import com.ezbid.dto.ProfileDto;
import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.User;
import com.ezbid.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// This class is a service that handles logics for users

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
    }

    // This method returns all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(userRepository.findAll());
        return users;
    }


    //  This method updates a user
    public User updateUser(User user, String firstName, String lastName) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepository.save(user);
    }

    // This method returns a user by id
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public ProfileDto getProfileDto(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new ProfileDto(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    public User profileDto2User(ProfileDto profileDto) {
        User user = new User();
        user.setUsername(profileDto.getUsername());
        user.setEmail(profileDto.getEmail());
        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        return user;
    }

}