package com.example.meajude.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.meajude.DTO.RegisterUserDTO;
import com.example.meajude.DTO.UserRegisteredDTO;
import com.example.meajude.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    

    @PostMapping("/users/register")
    public ResponseEntity<UserRegisteredDTO> register(@Valid @RequestBody RegisterUserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
    }


}
