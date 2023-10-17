package com.example.meajude.dtos;

import com.example.meajude.enums.DocumentType;

import com.example.meajude.entities.User;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Data;

@Data
public class RegisterUserDTO {
    

    @NotBlank(message = "Name is required")
    protected String name;

    @NotBlank(message = "Email is required")
    @Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
    protected String email;

    @NotBlank(message = "Password is required")
    protected String password;

    @NotBlank(message = "Phone is required")
    protected String phone;

    @NotBlank(message = "Document number is required")
    protected String documentNumber;

    // Enum validation
    @NotNull(message = "Document type is required")
    protected DocumentType documentType;

    protected boolean active = true;


    public RegisterUserDTO() {
    }

    public RegisterUserDTO(String name, String email, String password, String phone, String documentNumber, DocumentType documentType) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // Methods

    public RegisterUserDTO fromEntity(User user) {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setPhone(user.getPhone());
        dto.setDocumentNumber(user.getDocumentNumber());
        dto.setDocumentType(user.getDocumentType());
        dto.setActive(user.isActive());
        return dto;
    }

    public User toEntity() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setPhone(this.phone);
        user.setDocumentNumber(this.documentNumber);
        user.setDocumentType(this.documentType);
        user.setActive(this.active);
        return user;
    }


}
