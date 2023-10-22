package com.example.meajude.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meajude.entities.Campaign;


public interface CampaignDAO extends JpaRepository<Campaign , Long>{

    public Optional<Campaign> findByIdAndActiveTrue(long id);

}
