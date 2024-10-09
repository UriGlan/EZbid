package com.ezbid.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

// This class is the model for a user
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Auction> auctions;
    private boolean enabled = true;
    private String verificationCode;
    private Timestamp verificationCodeExpiration = Timestamp.valueOf(LocalDateTime.now().plusMinutes(15));


    // Empty constructor
    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    // Getters and Setters
    public Long getId() {
        return userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }
    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public boolean getEnabled() {
        return enabled;
    }
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCodeExpiration(LocalDateTime plusMinutes) {
        this.verificationCodeExpiration = verificationCodeExpiration;
    }
    public Timestamp getVerificationCodeExpiration() {
        return verificationCodeExpiration;
    }


    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    //need to implement these methods
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }



}
