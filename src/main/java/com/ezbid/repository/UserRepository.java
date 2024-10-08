package com.ezbid.repository;

import com.ezbid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This interface extends JpaRepository to perform CRUD operations on the User entity

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationCode(int verificationCode);
}
