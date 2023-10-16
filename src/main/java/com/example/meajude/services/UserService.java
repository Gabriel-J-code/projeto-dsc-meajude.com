package com.example.meajude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.meajude.dtos.RegisterUserDTO;
import com.example.meajude.dtos.UserRegisteredDTO;
import com.example.meajude.entities.User;
import com.example.meajude.repositories.UserDAO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserRegisteredDTO registerUser(RegisterUserDTO user) {

        if (userDAO.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User userEntity = new User(user.getName(), user.getEmail(), user.getPassword(), user.getPhone(),
            user.getDocumentNumber(), user.getDocumentType());

        userDAO.save(userEntity);

        return UserRegisteredDTO.fromEntity(userEntity);
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userDAO.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
