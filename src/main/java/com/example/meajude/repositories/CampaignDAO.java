package com.example.meajude.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.meajude.entities.Campaign;


public interface CampaignDAO extends JpaRepository<Campaign , Long>{

    public Optional<Campaign> findByIdAndActiveTrue(long id);
    public List<Campaign> findByActiveTrueOrderBySmallTitle();
    
    @Query("select c from Campaign c where c.collected >= c.goal")
    public List<Campaign> findCompletedCampaigns();

}
