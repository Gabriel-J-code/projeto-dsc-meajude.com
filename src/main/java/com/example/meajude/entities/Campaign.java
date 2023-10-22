package com.example.meajude.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.meajude.enums.State;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "campaigns")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String smallTitle;
    private String description;
    private double goal = 0;
    private double collected = 0;
    private LocalDateTime startDate;
    private LocalDate endDate;
    private boolean active = true;
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Donation> donations = new ArrayList<Donation>();
    

    public Campaign(String title, String smallTitle, String description, double goal, LocalDateTime startDate,
            LocalDate endDate, User user, State state) {
        this.title = title;
        this.smallTitle = smallTitle;
        this.description = description;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.state = state;
    }

    public Campaign(String title, String smallTitle, String description, double goal, LocalDateTime startDate, LocalDate endDate) {
        this.title = title;
        this.smallTitle = smallTitle;
        this.description = description;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallTitle() {
        return smallTitle;
    }

    public void setSmallTitle(String smallTitle) {
        this.smallTitle = smallTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }
    
    public double getCollected() {
        return collected;
    }

    public void setCollected(double collected) {
        this.collected = collected;
    } 

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Donation> getDonations(){
        return donations;
    }

    public void setDonations(List<Donation> donations){
        this.donations = donations;
    }

}
