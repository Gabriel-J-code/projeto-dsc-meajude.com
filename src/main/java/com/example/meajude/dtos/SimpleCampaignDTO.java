package com.example.meajude.dtos;

import java.time.format.DateTimeFormatter;

import com.example.meajude.entities.Campaign;

public class SimpleCampaignDTO {
    private long id;
    private String smallTitle;
    private double goal;
    private double collected;
    private String endDate;
    private String state;
    
    public SimpleCampaignDTO() {
    }

    public SimpleCampaignDTO(long id, String smallTitle, double goal, double collected, String endDate, String state) {
        this.id = id;
        this.smallTitle = smallTitle;
        this.goal = goal;
        this.collected = collected;
        this.endDate = endDate;
        this.state = state;
    }

    public SimpleCampaignDTO(Campaign campaign){
        id = campaign.getId();
        smallTitle = campaign.getSmallTitle();
        goal = campaign.getGoal();
        collected = campaign.getCollected();
        endDate = campaign.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        state = campaign.getState().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSmallTitle() {
        return smallTitle;
    }

    public void setSmallTitle(String smallTitle) {
        this.smallTitle = smallTitle;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
}
