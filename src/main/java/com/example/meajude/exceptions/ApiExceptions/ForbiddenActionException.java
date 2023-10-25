package com.example.meajude.exceptions.ApiExceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenActionException extends ApiException{

    public ForbiddenActionException(String msg) {
        super(msg, HttpStatus.FORBIDDEN.value());
    }

}
