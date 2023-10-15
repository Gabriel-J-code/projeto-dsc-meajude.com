package com.example.meajude.entities;


import com.example.meajude.enums.DocumentType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    private long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private boolean active = true;
    
    public User() {
    }

    public User(String name, String email, String password, String phone, String documentNumber, DocumentType documentType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
