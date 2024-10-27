package com.ezbid.controller;

import com.ezbid.dto.*;
import com.ezbid.model.User;
import com.ezbid.responses.LogInResponse;
import com.ezbid.service.AuthenticationService;
import com.ezbid.service.JwtService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is responsible for handling authentication requests
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    // Register a new user
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registerUser = authenticationService.signUp(registerUserDto);
        return ResponseEntity.ok(registerUser);
    }

    // Log in a user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            LogInResponse response = authenticationService.authenticate(loginUserDto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Verify a user
    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try{
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Send a password reset email
    @PostMapping("/reset-password-mail")
    public ResponseEntity<?> sendResetPasswordMail(@RequestBody MailAddressDto emailDto) throws MessagingException {
            authenticationService.sendPasswordEmail(emailDto);
            return ResponseEntity.ok("Password reset link sent successfully");
    }

    // Reset a user's password
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody NewPasswordDto newPasswordDto) {
        try {
            authenticationService.resetPassword(newPasswordDto);
            return ResponseEntity.ok("Password reset successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
