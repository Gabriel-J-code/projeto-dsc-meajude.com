package com.example.meajude.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.meajude.dtos.CampaignDTO;
import com.example.meajude.dtos.EditCampaignDTO;
import com.example.meajude.dtos.RegisterCampaingDTO;
import com.example.meajude.dtos.RegisterDonationDTO;
import com.example.meajude.entities.Campaign;
import com.example.meajude.entities.Donation;
import com.example.meajude.entities.User;
import com.example.meajude.enums.Role;
import com.example.meajude.enums.State;
import com.example.meajude.exceptions.ApiExceptions.CampaignAlreadyClosedException;
import com.example.meajude.exceptions.ApiExceptions.CampaignNotFoundException;
import com.example.meajude.exceptions.ApiExceptions.ForbiddenActionException;
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

    public CampaignDTO editCampaign(String authHeader, int id, EditCampaignDTO ecdto) {
        Campaign campaign = userCanEditCampaign(authHeader, id);
        validationCampaing(ecdto);
        campaign = ecdto.updateCampaign(campaign);
        return new CampaignDTO(campaignDAO.save(campaign));
    }    

    public Campaign userCanEditCampaign(String authHeader, int id){
        User user = userService.getUserToRequest(authHeader);
        Campaign campaign = getCampaignById(id);
        if(!(campaign.getUser()==user || user.getRole() == Role.ADMIN)){
            throw new ForbiddenActionException("The user cannot edit this campaign because they are not the owner and do not have an administrator role.");
        }
        if (!campaign.getEndDate().isAfter(LocalDate.now())){
            throw new ForbiddenActionException("Campaigns cannot be edited on or after the closing date.");
        }
        return campaign;
    }

    public void validationCampaing(EditCampaignDTO ecdto){ 
         
        if(!ecdto.getSmallTitle().isBlank() && ecdto.getSmallTitle().length() > 100){
            throw new InvalidFieldException("Small title cannot be longer than 100 characters");
        }         
        if(!ecdto.getDescription().isBlank() && ecdto.getDescription().length() > 1000){
            throw new InvalidFieldException("Description cannot be longer than 1000 characters");
        }
        if(ecdto != null &&(ecdto.getGoal() <= 0) ){
            throw new InvalidFieldException("Goal must be greater than zero");
        }
        if(!ecdto.getEndDate().isBlank() && (ecdto.getEndDateFormat().isBefore(LocalDate.now()) || ecdto.getEndDateFormat().isEqual(LocalDate.now()))){
            throw new InvalidFieldException("End Date must be in the future");
        }        
        if(!ecdto.getState().isBlank()){
            try {
                State.valueOf(ecdto.getState());
            } catch (IllegalArgumentException e) {
                List<String> states = new ArrayList<String>();
                for (State state : State.values()) {
                states.add(state.name());          
                }
                throw new InvalidFieldException(String.format("State must be in %s", states.toString()));
            }            
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
        campaign.setCollected(campaign.getCollected() + rdDTO.getValue());
        return new CampaignDTO(campaignDAO.save(campaign));
    }

    public Campaign getCampaignById(long id){
        Optional<Campaign> campaignOp = campaignDAO.findByIdAndActiveTrue(id);
        if (campaignOp.isEmpty()){
            throw new CampaignNotFoundException();
        }
        return campaignOp.get();
    }

    public List<CampaignDTO> getCampaignOrderBySmallTitle() {        
        return convertListCampaignToListSimple(campaignDAO.findByActiveTrueOrderBySmallTitle());
    }

    public List<CampaignDTO> convertListCampaignToListSimple(List<Campaign> campaigns){
        List<CampaignDTO> CampaignDTOs = new ArrayList<CampaignDTO>();
        for (Campaign campaign : campaigns) {
            CampaignDTOs.add(new CampaignDTO(campaign));            
        }
        return CampaignDTOs;
    }

    public List<CampaignDTO> findAllCompletedCampaigns() {
        return convertListCampaignToListSimple(campaignDAO.findCompletedCampaigns());
    }


    public List<CampaignDTO> findAllActiveCampaigns() {
        return convertListCampaignToListSimple(campaignDAO.findActiveCampaigns());
    }

    public List<CampaignDTO> findAllClosedCampaigns() {
        return convertListCampaignToListSimple(campaignDAO.findClosedCampaigns());
    }

    

    
    
}
