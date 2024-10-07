package com.ezbid.repository;

import com.ezbid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface extends JpaRepository to perform CRUD operations on the User entity

public interface UserRepository extends JpaRepository<User, Long> {
}
