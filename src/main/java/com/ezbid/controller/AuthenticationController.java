package com.ezbid.controller;

import com.ezbid.dto.LoginUserDto;
import com.ezbid.dto.RegisterUserDto;
import com.ezbid.dto.VerifyUserDto;
import com.ezbid.model.User;
import com.ezbid.responses.LogInResponse;
import com.ezbid.service.AuthenticationService;
import com.ezbid.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registerUser = authenticationService.signUp(registerUserDto);
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LogInResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authernicateUser = authenticationService.authenticate(loginUserDto);
        String jwt = jwtService.generateToken(authernicateUser);
        LogInResponse logInResponse = new LogInResponse(jwt, jwtService.getJwtExpirationMs());
        return ResponseEntity.ok(logInResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try{
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestBody String email) {
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
