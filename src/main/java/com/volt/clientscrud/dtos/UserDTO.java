package com.volt.clientscrud.dtos;

import com.volt.clientscrud.models.User;
import java.time.LocalDateTime;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String document;
    private LocalDateTime birthDate;
    private int age;

    public UserDTO(Long id, String name, String email, String password, String document, LocalDateTime birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.document = document;
        this.birthDate = birthDate;
        this.age = getAge();
    }
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(),user.getDocument(), user.getBirthDate());
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

    public int getAge() {
        if(birthDate.getDayOfMonth() < LocalDateTime.now().getDayOfMonth()
                &&
                birthDate.getMonth().getValue() < LocalDateTime.now().getMonth().getValue()){
            this.setAge(LocalDateTime.now().getYear() - birthDate.getYear());
        }else{
            this.setAge(LocalDateTime.now().getYear() - birthDate.getYear() - 1);
        }
        return age;
    }
    public void setAge(int age) {
        this.age = age;
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
    public LocalDateTime getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }
}
