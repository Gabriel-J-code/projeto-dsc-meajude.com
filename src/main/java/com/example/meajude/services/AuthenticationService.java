package com.example.meajude.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.meajude.dtos.JwtAuthenticationResponse;
import com.example.meajude.dtos.LoginDTO;
import com.example.meajude.dtos.RegisterUserDTO;
import com.example.meajude.entities.User;
import com.example.meajude.enums.Role;
import com.example.meajude.exceptions.ApiExceptions.UserNotFoundException;
import com.example.meajude.repositories.UserDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService{
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
   
    
    public JwtAuthenticationResponse signup(RegisterUserDTO request) {
        User user = new User(request.getName(),
                    request.getEmail(),
                    passwordEncoder.encode(request.getPassword()),
                    request.getPhone(),
                    request.getDocumentNumber(),
                    request.getDocumentType());
        /* User.builder().name(request.getName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).phone(request.getPhone()).documentNumber(request.getDocumentNumber()).documentType(request.getDocumentType()).active(true)
                .role(Role.USER).build();*/
        userDAO.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
    
    public JwtAuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userDAO.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
   
}