package com.ezbid.controller;


import com.ezbid.dto.ProfileDto;
import com.ezbid.dto.UserResponseDto;
import com.ezbid.exception.ResourceNotFoundException;
import com.ezbid.model.User;
import com.ezbid.repository.UserRepository;
import com.ezbid.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

// Controller for handling user-related requests
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // Get the authenticated user's profile
    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getProfileDto(userDetails.getUsername()));
    }

    // Get all users
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get a user by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserResponseDto response = new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
        return ResponseEntity.ok(response);
    }

    // Update the user's profile
    @PostMapping("/editprofile")
    public ResponseEntity<User> updateUser(@RequestBody Map<String,String>body, @AuthenticationPrincipal UserDetails userDetails) {
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return ResponseEntity.ok(userService.updateUser(user, firstName, lastName));
    }
}
