package com.ezbid.service;

import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.User;
import com.ezbid.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
        userRepository.findAll().forEach(users::add);
        return users;
    }
    // This method creates a user
    public User createUser(User user) {
        return userRepository.save(user);
    }
    //  This method updates a user
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    // This method deletes a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    // This method returns a user by id
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    // Additional methods for updating and deleting users
}