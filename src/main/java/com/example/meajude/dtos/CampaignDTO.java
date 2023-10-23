package com.example.meajude.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.meajude.entities.Campaign;

public class CampaignDTO {
    private long id;
    private String title;
    private String smallTitle;
    private String description;
    private double goal;
    private double collected;
    private String startDate;
    private String endDate;   
    private String user;
    private String state;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CampaignDTO(Campaign campaign){
        this.id = campaign.getId();
        this.title = campaign.getTitle();
        this.smallTitle = campaign.getSmallTitle();
        this.description = campaign.getDescription();
        this.goal = campaign.getGoal();
        this.collected = campaign.getCollected();
        this.startDate = campaign.getStartDate().format(dtf);
        this.endDate = campaign.getEndDate().format(dtf);
        this.user = campaign.getUser().getName();
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
        this.startDate = startDate.format(dtf);
        this.endDate = endDate.format(dtf);
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
