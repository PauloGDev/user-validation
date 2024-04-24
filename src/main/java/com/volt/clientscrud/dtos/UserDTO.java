package com.volt.clientscrud.dtos;

import com.volt.clientscrud.models.User;

import java.time.Instant;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String document;
    private Instant birthDate;

    public UserDTO(String name, String email, String password, String document, Instant birthDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.document = document;
        this.birthDate = birthDate;
    }
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.document = user.getDocument();
        this.birthDate = user.getBirthDate();
    }

    public UserDTO() {
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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public Instant getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }
}
