package com.example.meajude.exceptions.ApiExceptions;

import org.springframework.http.HttpStatus;

public class CampaignAlreadyClosedException extends ApiException{

    public CampaignAlreadyClosedException() {
        super("The campaign has already ended and cannot receive donations", HttpStatus.FORBIDDEN.value());
    }

}
