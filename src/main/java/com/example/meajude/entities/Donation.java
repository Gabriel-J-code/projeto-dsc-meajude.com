package com.example.meajude.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double donationValue;

    private LocalDateTime dateTime;
    
    @ManyToOne
    private User user;
    @ManyToOne
    private Campaign campaign;
    
    public Donation() {
    }
    public Donation(double value, LocalDateTime dateTime, User user, Campaign campaign) {
        this.donationValue = value;
        this.dateTime = dateTime;
        this.user = user;
        this.campaign = campaign;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getDonationValue() {
        return donationValue;
    }
    public void setDonationValue(double value) {
        this.donationValue = value;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Campaign getCampaign() {
        return campaign;
    }
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }    
    
}
