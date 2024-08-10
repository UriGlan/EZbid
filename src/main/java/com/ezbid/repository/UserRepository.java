package com.ezbid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ezbid.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods if needed
}