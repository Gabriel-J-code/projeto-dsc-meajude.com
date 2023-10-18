package com.example.meajude.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.meajude.entities.Campaign;

public class RegisterCampaingDTO {
    private String title;
    private String smallTitle;
    private String description;
    private double goal;
    private String startDate;
    private String endDate;

    public RegisterCampaingDTO() {
    }

    public RegisterCampaingDTO(String title, String smallTitle, String description, double goal, LocalDate startDate,
            LocalDate endDate) {
        this.title = title;
        this.smallTitle = smallTitle;
        this.description = description;
        this.goal = goal;
        this.startDate = startDate.toString();
        this.endDate = endDate.toString();
    }

    public Campaign toCampaing(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Campaign(title, smallTitle, description, goal, LocalDate.parse(startDate,dtf), LocalDate.parse(endDate, dtf));
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    

}
