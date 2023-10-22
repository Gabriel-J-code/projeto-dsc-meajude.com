package com.example.meajude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meajude.entities.Donation;

public interface DonationDAO extends JpaRepository<Donation, Long>{
    
}
