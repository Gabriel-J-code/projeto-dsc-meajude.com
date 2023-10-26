package com.example.meajude.dtos;

import java.time.format.DateTimeFormatter;

import com.example.meajude.entities.Donation;

public class DontaionDTO {
    private long id;
    private double value;
    private String dataTime;
    private String campaignSmallTitle;
    
    public DontaionDTO() {
    }

    public DontaionDTO(long id, double value, String dataTime, String campaignSmallTitle) {
        this.id = id;
        this.value = value;
        this.dataTime = dataTime;
        this.campaignSmallTitle = campaignSmallTitle;
    }
    public DontaionDTO(Donation donation) {
        this(donation.getId(), 
        donation.getDonationValue(), 
        donation.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
        donation.getCampaign().getSmallTitle());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getCampaignSmallTitle() {
        return campaignSmallTitle;
    }

    public void setCampaignSmallTitle(String campaignSmallTitle) {
        this.campaignSmallTitle = campaignSmallTitle;
    }
    
    
    
}
