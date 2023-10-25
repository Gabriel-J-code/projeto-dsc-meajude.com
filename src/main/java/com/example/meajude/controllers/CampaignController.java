package com.example.meajude.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meajude.dtos.CampaignDTO;
import com.example.meajude.dtos.EditCampaignDTO;
import com.example.meajude.dtos.RegisterCampaingDTO;
import com.example.meajude.dtos.RegisterDonationDTO;
import com.example.meajude.services.CampaignService;

@RestController
@RequestMapping("/api/v1/campaign")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @PostMapping()
    public ResponseEntity<CampaignDTO> registerCampaign(@RequestHeader("Authorization") String authHeader, @RequestBody RegisterCampaingDTO registerCampaingDTO){
        return new ResponseEntity<CampaignDTO>(campaignService.registerCampaing(authHeader,registerCampaingDTO), HttpStatus.OK);
    } 

    @GetMapping()
    public ResponseEntity<List<CampaignDTO>> getCampaigns(){
        return new ResponseEntity<List<CampaignDTO>>(campaignService.getCampaignOrderBySmallTitle(), HttpStatus.OK);
    }

    @GetMapping("/completeds")
    public ResponseEntity<List<CampaignDTO>> findAllCompletedCampaigns(){
        return new ResponseEntity<List<CampaignDTO>>(campaignService.findAllCompletedCampaigns(), HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<CampaignDTO>> findAllActiveCampaigns(){
        return new ResponseEntity<List<CampaignDTO>>(campaignService.findAllActiveCampaigns(), HttpStatus.OK);
    }

    @GetMapping("/closed")
    public ResponseEntity<List<CampaignDTO>> findAllFinishedCampaigns(){
        return new ResponseEntity<List<CampaignDTO>>(campaignService.findAllClosedCampaigns(), HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<CampaignDTO> editCampaign(@RequestHeader("Authorization") String authHeader, @PathVariable int id, @RequestBody EditCampaignDTO ntcdto){
        return new ResponseEntity<CampaignDTO>(campaignService.editCampaign(authHeader, id, ntcdto),  HttpStatus.OK);
    }

    //donatios
    @PostMapping("/{id}/donations")
    public ResponseEntity<CampaignDTO> registerDonation(@RequestHeader("Authorization") String authHeader, @PathVariable int id, @RequestBody RegisterDonationDTO rdDTO){
        return new ResponseEntity<CampaignDTO>(campaignService.registerDonation(authHeader, id, rdDTO), HttpStatus.OK);
    }
    
    
}
