package com.example.meajude.exceptions.ApiExceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExitsException extends ApiException {

    public UserAlreadyExitsException() {
        super("An user with this email already exists.", HttpStatus.CONFLICT.value());
    }
}
