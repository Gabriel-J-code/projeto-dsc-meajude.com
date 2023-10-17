package com.example.meajude.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meajude.dtos.JwtAuthenticationResponse;
import com.example.meajude.dtos.LoginDTO;
import com.example.meajude.dtos.RegisterUserDTO;
import com.example.meajude.dtos.UserRegisteredDTO;
//import com.example.meajude.dtos.SignUpRequestDTO;
import com.example.meajude.services.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    
    @PostMapping("/register")
    public ResponseEntity<UserRegisteredDTO> signup(@Valid @RequestBody RegisterUserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
