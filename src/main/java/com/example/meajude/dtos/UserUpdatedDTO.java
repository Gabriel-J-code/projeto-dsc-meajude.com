package com.example.meajude.dtos;

import com.example.meajude.entities.User;
import com.example.meajude.enums.DocumentType;

public class UserUpdatedDTO {

    private Long id;
    
    private String email;

    private String name;

    private String phone;

    private String documentNumber;

    private DocumentType documentType;

    private Boolean active;


    public UserUpdatedDTO(){}

    public UserUpdatedDTO(Long id, String email, String name, String phone, String documentNumber, DocumentType documentType, Boolean active) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.active = active;
    }

    // getters

    public Long getId() {
        return id;
    }

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

    public Boolean getActive() {
        return active;
    }

    //Methods

    public static UserUpdatedDTO fromEntity(User user){
        return new UserUpdatedDTO(
            user.getId(),
            user.getEmail(),
            user.getName(),
            user.getPhone(),
            user.getDocumentNumber(),
            user.getDocumentType(),
            user.isActive()
        );
    }
}
