package com.example.meajude.exceptions.ApiExceptions;

import org.springframework.http.HttpStatus;

public class CampaignNotFoundException extends ApiException{

    public CampaignNotFoundException() {
        super("Id Campaing provided is not associated with a registed active campaign", HttpStatus.NOT_FOUND.value());
    }

}
