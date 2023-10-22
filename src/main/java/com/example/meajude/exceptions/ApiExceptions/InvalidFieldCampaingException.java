package com.example.meajude.exceptions.ApiExceptions;

import org.springframework.http.HttpStatus;

public class InvalidFieldCampaingException extends ApiException{

    public InvalidFieldCampaingException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST.value());
    }

}
