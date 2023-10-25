package com.example.meajude.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.meajude.entities.Campaign;
import com.example.meajude.enums.State;

public class EditCampaignDTO {
    private String title;
    private String smallTitle;
    private String description;
    private double goal = 0;
    private String endDate;    
    private String state;
    
    public EditCampaignDTO() {
    }

    public Campaign updateCampaign(Campaign campaign){
        if(!title.isBlank()){
            campaign.setTitle(title);
        }
        if(!smallTitle.isBlank()){
        campaign.setSmallTitle(smallTitle);
        }
        if(!description.isBlank()){
        campaign.setDescription(description);
        }
        if (goal != 0) {
        campaign.setGoal(goal);
        }
        if(!endDate.isBlank()){
        campaign.setEndDate(getEndDateFormat());
        }
        if(!state.isBlank()){
        campaign.setState(State.valueOf(getState()));
        }
        return campaign;
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

    public String getState() {
        return state.toUpperCase();
    }

    public void setState(String state) {
        this.state = state;
    }

    
}
