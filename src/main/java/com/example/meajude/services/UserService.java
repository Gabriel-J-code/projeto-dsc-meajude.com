package com.example.meajude.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.meajude.entities.User;
import com.example.meajude.exceptions.ApiExceptions.UserNotFoundException;
import com.example.meajude.repositories.UserDAO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private JwtService jwtService;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userDAO.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public User getUserByEmail(String userEmail) {
        Optional<User> usuarioOp = userDAO.findByEmail(userEmail);
        if (usuarioOp.isEmpty()) {            
            throw new UserNotFoundException();      
        }
        return usuarioOp.get();
    }
    public User getUserToRequest(String authHeader){
        String userEmail = jwtService.extractUserName(authHeader);
        return getUserByEmail(userEmail);
    }
}
