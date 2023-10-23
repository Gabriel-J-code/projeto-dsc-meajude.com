package com.example.meajude.dtos;

import com.example.meajude.enums.DocumentType;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateUserDTO {

    
    @Email(message = "The email address is invalid.")
    private String email;

    private String name;

    private String phone;

    private String documentNumber;

    protected DocumentType documentType;

    private String password;

    public UpdateUserDTO(){}

    public UpdateUserDTO(String email, String name, String phone, String documentNumber, DocumentType documentType, String password) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.password = password;
    }

    // getters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getPassword() {
        return password;
    }

    


}
