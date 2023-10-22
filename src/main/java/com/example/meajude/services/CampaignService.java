package com.example.meajude.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.meajude.dtos.CampaignDTO;
import com.example.meajude.dtos.RegisterCampaingDTO;
import com.example.meajude.dtos.RegisterDonationDTO;
import com.example.meajude.dtos.SimpleCampaignDTO;
import com.example.meajude.entities.Campaign;
import com.example.meajude.entities.Donation;
import com.example.meajude.entities.User;
import com.example.meajude.enums.State;
import com.example.meajude.exceptions.ApiExceptions.CampaignAlreadyClosedException;
import com.example.meajude.exceptions.ApiExceptions.CampaignNotFoundException;
import com.example.meajude.exceptions.ApiExceptions.InvalidFieldException;
import com.example.meajude.repositories.CampaignDAO;
import com.example.meajude.repositories.DonationDAO;


@Service
public class CampaignService {

    @Autowired
    private CampaignDAO campaignDAO;
    @Autowired
    private DonationDAO donationDAO;
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
            throw new InvalidFieldException("Small title cannot be longer than 100 characters");
        }
        validationStringField(rcdto.getDescription(), "Description is required"); 
        if(rcdto.getDescription().length() > 1000){
            throw new InvalidFieldException("Description cannot be longer than 1000 characters");
        }
        if((rcdto.getGoal() <= 0) ){
            throw new InvalidFieldException("Goal must be greater than zero");
        }
        validationStringField(rcdto.getEndDate(), "End Date is required");
        if(rcdto.getEndDateFormat().isBefore(LocalDate.now()) || rcdto.getEndDateFormat().isEqual(LocalDate.now())){
            throw new InvalidFieldException("End Date must be in the future");
        }
    }

    public void validationStringField(String field, String messageException){
        if(field.isBlank()){
            throw new InvalidFieldException(messageException);
        }
    }

    //donation
    public CampaignDTO registerDonation(String authHeader, int id, RegisterDonationDTO rdDTO) {
        if(rdDTO.getValue() <= 0){
            throw new InvalidFieldException("Donation Value must be greater than zero");
        }
        User user = userService.getUserToRequest(authHeader);
        Campaign campaign = getCampaignById(id);
        if(campaign.getState().equals(State.CLOSED)){
            throw new CampaignAlreadyClosedException();
        }
        Donation donation = new Donation(rdDTO.getValue(), LocalDateTime.now(), user, campaign);
        donationDAO.save(donation);
        return new CampaignDTO(getCampaignById(id));
    }

    public Campaign getCampaignById(long id){
        Optional<Campaign> campaignOp = campaignDAO.findByIdAndActiveTrue(id);
        if (campaignOp.isEmpty()){
            throw new CampaignNotFoundException();
        }
        return campaignOp.get();
    }

    public List<SimpleCampaignDTO> getCampaignOrderBySmallTitle() {        
        return convertListCampaignToListSimple(campaignDAO.findByActiveTrueOrderBySmallTitle());
    }

    public List<SimpleCampaignDTO> convertListCampaignToListSimple(List<Campaign> campaigns){
        List<SimpleCampaignDTO> simpleCampaignDTOs = new ArrayList<SimpleCampaignDTO>();
        for (Campaign campaign : campaigns) {
            simpleCampaignDTOs.add(new SimpleCampaignDTO(campaign));            
        }
        return simpleCampaignDTOs;
    }

    public List<SimpleCampaignDTO> findAllCompletedCampaigns() {
        return convertListCampaignToListSimple(campaignDAO.findCompletedCampaigns());
    }
    
}
