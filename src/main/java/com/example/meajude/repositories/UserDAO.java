package com.example.meajude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meajude.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {
    
    Boolean existsByEmail(String email);
}