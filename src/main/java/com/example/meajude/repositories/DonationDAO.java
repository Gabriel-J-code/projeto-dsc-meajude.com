package com.example.meajude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meajude.entities.Donation;
import java.util.List;


public interface DonationDAO extends JpaRepository<Donation, Long>{
    public List<Donation> findAllByCampaignActiveTrueOrderByDateTimeAsc();
    public List<Donation> findAllByCampaignId(long id);
}
