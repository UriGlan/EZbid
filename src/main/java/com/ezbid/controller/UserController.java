package com.ezbid.controller;


import com.ezbid.dto.ProfileDto;
import com.ezbid.dto.UserResponseDto;
import com.ezbid.model.User;
import com.ezbid.repository.UserRepository;
import com.ezbid.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getProfileDto(userDetails.getUsername()));
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserResponseDto response = new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
        return ResponseEntity.ok(response);
    }
}
