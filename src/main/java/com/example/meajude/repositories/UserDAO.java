package com.example.meajude.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meajude.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {
    
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}