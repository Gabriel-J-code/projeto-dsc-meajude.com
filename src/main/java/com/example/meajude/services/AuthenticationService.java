package com.example.meajude.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.meajude.dtos.JwtAuthenticationResponse;
import com.example.meajude.dtos.LoginDTO;
import com.example.meajude.dtos.RegisterUserDTO;
import com.example.meajude.dtos.UserRegisteredDTO;
import com.example.meajude.entities.User;
import com.example.meajude.exceptions.ApiExceptions.UserAlreadyExitsException;
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
       
    public UserRegisteredDTO registerUser(RegisterUserDTO user) {

        if (userDAO.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExitsException();        
        }

        User userEntity = new User(user.getName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getPhone(),
            user.getDocumentNumber(), user.getDocumentType());

        userDAO.save(userEntity);

        return UserRegisteredDTO.fromEntity(userEntity);
    }
    
    public JwtAuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if(!userDAO.existsByEmail(request.getEmail())){
            throw new UserNotFoundException();
        }
        var user = userDAO.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
   
}