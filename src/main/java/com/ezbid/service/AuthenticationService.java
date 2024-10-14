package com.ezbid.service;

import com.ezbid.dto.*;
import com.ezbid.model.PasswordResetToken;
import com.ezbid.model.User;
import com.ezbid.repository.PasswordResetTokenRepository;
import com.ezbid.repository.UserRepository;
import com.ezbid.responses.LogInResponse;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.desktop.SystemSleepEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 EmailService emailService,
                                 JwtService jwtService, PasswordResetTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
    }

    // This method signs up a user and send a verification email
    public User signUp(RegisterUserDto input) {
        User user = new User(input.getUsername(),
                passwordEncoder.encode(input.getPassword()),
                input.getEmail(),
                input.getFirstname(),
                input.getLastname());
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiration(Timestamp.valueOf(LocalDateTime.now().plusMinutes(15)));
        user.setEnabled(false);
        sendVerificationEmail(user);
        return userRepository.save(user);
    }

    // This method authenticates a user and return the JWT token
    public LogInResponse authenticate(LoginUserDto input) throws RuntimeException {
        User user = userRepository.findByEmail(input.getEmail().trim())
                .orElseThrow(() -> new RuntimeException("User not found " + input.getEmail()));
        if (!user.isEnabled()) {
            if(user.getVerificationCodeExpiration().before(Timestamp.valueOf(LocalDateTime.now()))){
                resendVerificationCode(user);
                throw new RuntimeException("Account not verified. Verification code expired. A new code has been sent to your email.");
            }
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
        Optional<User> optionalUser = userRepository.findByEmail(input.getEmail().trim());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCodeExpiration().before(Timestamp.valueOf(LocalDateTime.now()))) {
                resendVerificationCode(user);
                throw new RuntimeException("Verification code expired. A new code has been sent to your email.");
            }
            if (user.getVerificationCode().equals(input.getVerificationCode())) {
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiration(null);
                userRepository.save(user);
            } else {
                throw new RuntimeException("Invalid verification code. Please check you email and try again.");
            }
        }
    }

    // In case the user did not receive the verification code, this method resends the verification code
    public void resendVerificationCode(User user){
        System.out.println("Resending verification code");
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiration(Timestamp.valueOf((LocalDateTime.now().plusMinutes(15))));
        sendVerificationEmail(user);
        userRepository.save(user);
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
    public void sendPasswordEmail(MailAddressDto emailDto) throws MessagingException {
        String email = emailDto.getEmail();
        System.out.println("Searching for email: " + email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found " + email));

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setExpirationTime(LocalDateTime.now().plusMinutes(15));
        passwordResetToken.setUser(user);
        tokenRepository.save(passwordResetToken);
        emailService.sendPasswordResetEmail(email, token);
    }
    @Transactional
    public void resetPassword(NewPasswordDto newPasswordDto) {
        String token = newPasswordDto.getToken();
        String email = newPasswordDto.getEmail();
        String password = newPasswordDto.getPassword();
        System.out.println("Searching for token: " + token+ " email: "+ email + " password: "+ password);
        PasswordResetToken passwordResetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (passwordResetToken.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }
        User user1 = passwordResetToken.getUser();
        User user2 = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found " + email));
        if (!user1.equals(user2)) {
            throw new RuntimeException("Invalid user");
        }
        user1.setPassword(passwordEncoder.encode(password));
        userRepository.save(user1);
        tokenRepository.deleteByToken(token);
    }



    // Generates a random 4-digit verification code
    public String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }
}
