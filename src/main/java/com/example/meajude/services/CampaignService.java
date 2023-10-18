package com.example.meajude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.meajude.dtos.CampaignDTO;
import com.example.meajude.dtos.RegisterCampaingDTO;
import com.example.meajude.entities.Campaign;
import com.example.meajude.entities.User;
import com.example.meajude.enums.State;
import com.example.meajude.repositories.CampaignDAO;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CampaignService {

    @Autowired
    private CampaignDAO campaignDAO;
    @Autowired
    private UserService userService;

    public CampaignDTO registerCampaing(String authHeader, RegisterCampaingDTO rCDto) {
        User user = userService.getUserToRequest(authHeader);
        Campaign campaign = rCDto.toCampaing();
        campaign.setUser(user);
        campaign.setState(State.ACTIVE);              
        return new CampaignDTO(campaignDAO.save(campaign));
    }

}
