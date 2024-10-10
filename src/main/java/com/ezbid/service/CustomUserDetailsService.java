package com.ezbid.service;

import com.ezbid.model.User;
import com.ezbid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Log the email for debugging
        System.out.println("Looking for user with email: " + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("User not found with email: " + email); // Log if user not found
                    return new UsernameNotFoundException("User not found with email: " + email);
                });

        System.out.println("User found: " + user.getEmail()); // Log when user is found

        // Return UserDetails for Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),  // Username
                user.getPassword(),  // Password
                user.isEnabled(),    // Whether the user is enabled
                true,                // Account is not expired
                true,                // Credentials are not expired
                true,                // Account is not locked
                Collections.emptyList() // No authorities for now
        );
    }
}
