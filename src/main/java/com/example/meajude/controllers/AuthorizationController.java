package com.example.meajude.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.meajude.services.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/resource")
@RequiredArgsConstructor
public class AuthorizationController {
    //private final JwtService jwts;
    @GetMapping
    public ResponseEntity<String> sayHello(HttpServletRequest request) {
        String token = request.getHeader("Authorization");        
        //String user = jwts.extractUserName(token);
        return ResponseEntity.ok(token) ;
    }
}