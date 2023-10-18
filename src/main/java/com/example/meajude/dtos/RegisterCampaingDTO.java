package com.example.meajude.dtos;

import java.time.LocalDate;

import com.example.meajude.entities.Campaign;

public class RegisterCampaingDTO {
    private String title;
    private String smallTitle;
    private String description;
    private double goal;
    private LocalDate startDate;
    private LocalDate endDate;

    public RegisterCampaingDTO(String title, String smallTitle, String description, double goal, LocalDate startDate,
            LocalDate endDate) {
        this.title = title;
        this.smallTitle = smallTitle;
        this.description = description;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Campaign toCampaing(){
        return new Campaign(title, smallTitle, description, goal, startDate, endDate);
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

    

}
