package com.volt.clientscrud.services;

import com.volt.clientscrud.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest{

    UserRepository userRepository;
    @Test
    void checkDocument() {
        UserService service = new UserService(userRepository);
        String document = "14538220620";
        Boolean result = service.checkDocument(document);
        assertTrue(result);
    }
}