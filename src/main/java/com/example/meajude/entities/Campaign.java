package com.example.meajude.entities;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import com.example.meajude.enums.State;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private double goal;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active = true;

    @ManyToAny
    private User user;
    @Enumerated(EnumType.STRING)
    private State state;

    public Campaign(String title, String smallTitle, String description, double goal, LocalDate startDate,
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
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

}
