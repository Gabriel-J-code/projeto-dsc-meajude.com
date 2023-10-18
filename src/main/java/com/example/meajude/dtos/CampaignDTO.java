package com.example.meajude.dtos;

import java.time.LocalDate;

import com.example.meajude.entities.Campaign;

public class CampaignDTO {
    private long id;
    private String title;
    private String smallTitle;
    private String description;
    private double goal;
    private double collected;
    private LocalDate startDate;
    private LocalDate endDate;   
    private String user;
    private String state;

    public CampaignDTO(Campaign campaign){
        this.id = campaign.getId();
        this.title = campaign.getTitle();
        this.smallTitle = campaign.getSmallTitle();
        this.description = campaign.getDescription();
        this.goal = campaign.getGoal();
        this.collected = campaign.getCollected();
        this.startDate = campaign.getStartDate();
        this.endDate = campaign.getEndDate();
        this.user = campaign.getUser().getEmail();
        this.state = campaign.getState().name();
    }

    public CampaignDTO(long id, String title, String smallTitle, String description, double goal, double collected,
            LocalDate startDate, LocalDate endDate, String user, String state) {
        this.id = id;
        this.title = title;
        this.smallTitle = smallTitle;
        this.description = description;
        this.goal = goal;
        this.collected = collected;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }    
}
