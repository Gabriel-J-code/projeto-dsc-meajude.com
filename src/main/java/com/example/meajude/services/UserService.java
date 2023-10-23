package com.example.meajude.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.meajude.dtos.UpdateUserDTO;
import com.example.meajude.dtos.UserUpdatedDTO;
import com.example.meajude.entities.User;
import com.example.meajude.enums.Role;
import com.example.meajude.exceptions.ApiExceptions.UnauthorizedActionException;
import com.example.meajude.exceptions.ApiExceptions.UserAlreadyExitsException;
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

    public UserUpdatedDTO updateUser(String authHeader, Long userId, UpdateUserDTO updateUserDTO) {
        
        User authUser = getUserToRequest(authHeader);

        if (authUser.getId() != userId && authUser.getRole() != Role.ADMIN) {
            throw new UnauthorizedActionException();
        }

        User user = userDAO.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        if (updateUserDTO.getEmail() != null) {

            Optional<User> userOp = userDAO.findByEmail(updateUserDTO.getEmail());

            if (userOp.isPresent() && userOp.get().getId() != userId) {
                throw new UserAlreadyExitsException();
            }

            user.setEmail(updateUserDTO.getEmail());
        }

        if (updateUserDTO.getName() != null) {
            user.setName(updateUserDTO.getName());
        }

        if (updateUserDTO.getPhone() != null) {
            user.setPhone(updateUserDTO.getPhone());
        }

        if (updateUserDTO.getDocumentNumber() != null) {
            user.setDocumentNumber(updateUserDTO.getDocumentNumber());
        }

        if (updateUserDTO.getDocumentType() != null) {
            user.setDocumentType(updateUserDTO.getDocumentType());
        }

        if (updateUserDTO.getPassword() != null) {
            user.setPassword(updateUserDTO.getPassword());
        }
        
        userDAO.save(user);

        UserUpdatedDTO userUpdatedDTO = UserUpdatedDTO.fromEntity(user);

        return userUpdatedDTO;
    }

    public UserUpdatedDTO deleteUser(String authHeader, Long userId) {
        User authUser = getUserToRequest(authHeader);

        if (authUser.getId() != userId && authUser.getRole() != Role.ADMIN) {
            throw new UnauthorizedActionException();
        }

        User user = userDAO.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        user.setActive(false);

        userDAO.save(user);

        return UserUpdatedDTO.fromEntity(user);
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
