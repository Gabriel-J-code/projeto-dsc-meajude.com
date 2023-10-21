package com.example.meajude.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meajude.dtos.CampaignDTO;
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

    @PostMapping("/{id}/donations")
    public ResponseEntity<CampaignDTO> registerDonation(@RequestHeader("Authorization") String authHeader, @PathVariable int id, @RequestBody RegisterDonationDTO rdDTO){
        return new ResponseEntity<CampaignDTO>(campaignService.registerDonation(authHeader, id, rdDTO), HttpStatus.OK);
    }
    
    @GetMapping()
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

    
}
