package com.example.meajude.services;

import com.example.meajude.dto.JwtAuthenticationResponse;
import com.example.meajude.dto.LoginDTO;
import com.example.meajude.dto.SignUpRequestDTO;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequestDTO request);

    JwtAuthenticationResponse login(LoginDTO request);
}
