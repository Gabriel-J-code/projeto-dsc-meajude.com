package com.example.meajude.dtos;

import com.example.meajude.enums.DocumentType;

import com.example.meajude.entities.User;

public class UserRegisteredDTO {
    
    public String name;
    public String email;
    public String phone;
    public String documentNumber;


    public DocumentType documentType;
    public boolean active = true;


    public UserRegisteredDTO() {
    }

    public UserRegisteredDTO(String name, String email, String phone, String documentNumber, DocumentType documentType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
    }

    // Getters

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public String getPhone() {
        return phone;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    // Methods

    public static UserRegisteredDTO fromEntity(User user) {
        return new UserRegisteredDTO(
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getDocumentNumber(),
            user.getDocumentType()
        );
    }




}

