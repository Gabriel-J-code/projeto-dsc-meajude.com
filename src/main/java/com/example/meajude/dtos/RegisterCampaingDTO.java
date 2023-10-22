package com.example.meajude.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.meajude.entities.Campaign;
import com.example.meajude.entities.User;
import com.example.meajude.enums.State;

public class RegisterCampaingDTO {
    private String title;
    private String smallTitle;
    private String description;
    private double goal;
    private String endDate;

    public RegisterCampaingDTO() {
    }

    public RegisterCampaingDTO(String title, String smallTitle, String description, double goal,
            String endDate) {
        this.title = title;
        this.smallTitle = smallTitle;
        this.description = description;
        this.goal = goal;
        this.endDate = endDate;
    }

    public Campaign toCampaing(User user, State state) {
        return new Campaign(title, smallTitle, description, goal, LocalDateTime.now(), getEndDateFormat(), user, state);
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

    public String getEndDate() {
        return endDate;
    }

    public LocalDate getEndDateFormat(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(endDate, dtf);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
