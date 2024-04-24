package com.volt.clientscrud.controllers;

import com.volt.clientscrud.dtos.UserDTO;
import com.volt.clientscrud.models.User;
import com.volt.clientscrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findOneById(@PathVariable Long id){
        UserDTO user = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto){
        User user = service.insert(dto);
        dto = new UserDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto){
        User user = service.update(dto, id);
        dto = new UserDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
}
