package com.example.meajude.exceptions.ApiExceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiException{

    public UserNotFoundException() {
        super("The email provided is not associated with a user.", HttpStatus.CONFLICT.value());
    }
    
}
