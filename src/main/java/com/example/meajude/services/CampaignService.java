package com.example.meajude.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.meajude.dtos.CampaignDTO;
import com.example.meajude.dtos.RegisterCampaingDTO;
import com.example.meajude.entities.Campaign;
import com.example.meajude.entities.User;
import com.example.meajude.enums.State;
import com.example.meajude.exceptions.ApiExceptions.InvalidFieldCampaingException;
import com.example.meajude.repositories.CampaignDAO;


@Service
public class CampaignService {

    @Autowired
    private CampaignDAO campaignDAO;
    @Autowired
    private UserService userService;

    public CampaignDTO registerCampaing(String authHeader, RegisterCampaingDTO rCDto) {
        validationCampaing(rCDto);
        User user = userService.getUserToRequest(authHeader);
        Campaign campaign = rCDto.toCampaing(user, State.ACTIVE);            
        return new CampaignDTO(campaignDAO.save(campaign));
    }

    public void validationCampaing(RegisterCampaingDTO rcdto){ 
        validationStringField(rcdto.getTitle(), "Title is required");  
        validationStringField(rcdto.getSmallTitle(), "Small Title is required"); 
        if(rcdto.getSmallTitle().length() > 100){
            throw new InvalidFieldCampaingException("Small title cannot be longer than 100 characters");
        }
        validationStringField(rcdto.getDescription(), "Description is required"); 
        if(rcdto.getDescription().length() > 1000){
            throw new InvalidFieldCampaingException("Description cannot be longer than 1000 characters");
        }
        if((rcdto.getGoal() <= 0) ){
            throw new InvalidFieldCampaingException("Goal must be greater than zero " + rcdto.getGoal());
        }
        validationStringField(rcdto.getEndDate(), "End Date is required");
        if(rcdto.getEndDateFormat().isBefore(LocalDate.now()) || rcdto.getEndDateFormat().isEqual(LocalDate.now())){
            throw new InvalidFieldCampaingException("End Date must be in the future");
        }
    }

    public void validationStringField(String field, String messageException){
        if(field.isBlank()){
            throw new InvalidFieldCampaingException(messageException);
        }
    }

}
