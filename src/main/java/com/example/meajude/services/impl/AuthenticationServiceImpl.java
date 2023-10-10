package com.example.meajude.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.meajude.dto.JwtAuthenticationResponse;
import com.example.meajude.dto.LoginDTO;
import com.example.meajude.dto.SignUpRequestDTO;
import com.example.meajude.entities.Role;
import com.example.meajude.entities.User;
import com.example.meajude.repositories.UserRepository;
import com.example.meajude.services.AuthenticationService;
import com.example.meajude.services.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    @Override
    public JwtAuthenticationResponse signup(SignUpRequestDTO request) {
        var user = User.builder().name(request.getName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).phone(request.getPhone()).numDoc(request.getNumDoc()).typeDoc(request.getTypeDoc())
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
   
}