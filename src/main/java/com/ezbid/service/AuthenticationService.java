package com.ezbid.service;

import com.ezbid.dto.LoginUserDto;
import com.ezbid.dto.RegisterUserDto;
import com.ezbid.dto.VerifyUserDto;
import com.ezbid.model.User;
import com.ezbid.repository.UserRepository;
import com.ezbid.responses.LogInResponse;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 EmailService emailService,
                                 JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    // This method signs up a user and send a verification email
    public User signUp(RegisterUserDto input) {
        User user = new User(input.getUsername(),passwordEncoder.encode(input.getPassword()), input.getEmail());
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(15));
        user.setEnabled(false);
        sendVerificationEmail(user);
        return userRepository.save(user);
    }

    // This method authenticates a user and return the JWT token
    public LogInResponse authenticate(LoginUserDto input) {
        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found " + input.getEmail()));
        System.out.println("mail = " + user.getEmail() + " name= "+ user.getUsername() +"Id= " + user.getId());
        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified. Please check your email for the verification code.");
        }
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        input.getPassword()
                )
        );
        // Generate the JWT token
        String token = jwtService.generateToken(user);
        long expiresIn = 3600000;
        return new LogInResponse(token, expiresIn);
    }

    // This method verifies the users mail with a code
    public void verifyUser(VerifyUserDto input) {
        Optional<User> optionalUser = userRepository.findByEmail(input.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCodeExpiration().before(Timestamp.valueOf(LocalDateTime.now()))) {
                throw new RuntimeException("Verification code expired. Please sign up again.");
            }
            if (user.getVerificationCode().equals(input.getVerificationCode())) {
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiration(null);
                userRepository.save(user);
            } else {
                throw new RuntimeException("Invalid verification code. Please try again.");
            }
        }
    }

    // In case the user did not receive the verification code, this method resends the verification code
    public void resendVerificationCode(String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isEnabled()) {
                throw new RuntimeException("Account already verified.");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(15));
            sendVerificationEmail(user);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Account not found.");
        }
    }

    // This method sends a verification email to the user
    public void sendVerificationEmail(User user) {
        String subject = "Account Verification";
        String verification = user.getVerificationCode();
        String body = "Your verification code is: " + verification;
        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // logic for password reset token


    // Generates a random 4-digit verification code
    public String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }
}
