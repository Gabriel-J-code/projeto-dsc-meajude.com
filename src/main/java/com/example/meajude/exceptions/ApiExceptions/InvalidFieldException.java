package com.example.meajude.exceptions.ApiExceptions;

import org.springframework.http.HttpStatus;

public class InvalidFieldException extends ApiException{

    public InvalidFieldException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST.value());
    }

}
