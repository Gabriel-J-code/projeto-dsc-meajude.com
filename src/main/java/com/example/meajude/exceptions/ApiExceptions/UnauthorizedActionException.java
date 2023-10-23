package com.example.meajude.exceptions.ApiExceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedActionException extends ApiException {
    
    public UnauthorizedActionException() {
        super("You are not authorized to perform this action.", HttpStatus.UNAUTHORIZED.value());
    }
}
