package com.example.meajude.dtos;

import com.example.meajude.enums.DocumentType;

public class UserUpdatedDTO {

    private Long id;
    
    private String email;

    private String name;

    private String phone;

    private String documentNumber;

    protected DocumentType documentType;


    public UserUpdatedDTO(){}

    public UserUpdatedDTO(Long id, String email, String name, String phone, String documentNumber, DocumentType documentType) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
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
}
