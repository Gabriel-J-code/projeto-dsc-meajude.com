package com.example.meajude.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meajude.dtos.UpdateUserDTO;
import com.example.meajude.dtos.UserUpdatedDTO;
import com.example.meajude.services.UserService;


@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    
    @PatchMapping("users/{id}")
    public ResponseEntity<UserUpdatedDTO> update(@RequestHeader("Authorization") String authHeader,@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO){
        return ResponseEntity.ok(userService.updateUser(authHeader, id, updateUserDTO));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<UserUpdatedDTO> delete(@RequestHeader("Authorization") String authHeader,@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(authHeader, id));
    }


}
