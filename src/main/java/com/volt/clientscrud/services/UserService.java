package com.volt.clientscrud.services;

import com.volt.clientscrud.controllers.exceptions.DataAlreadyExists;
import com.volt.clientscrud.controllers.exceptions.EntityNotFound;
import com.volt.clientscrud.controllers.exceptions.NotAcceptableParameters;
import com.volt.clientscrud.dtos.UserDTO;
import com.volt.clientscrud.models.User;
import com.volt.clientscrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public UserDTO findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new EntityNotFound("Entity Not Found"));
        return new UserDTO(entity);
    }
    public User update(UserDTO dto, Long id){
        dto.setId(id);
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new EntityNotFound("Entity Not Found"));
        return userRepository.save(CopyDtoToEntity(dto, entity));
    }
    public User insert(UserDTO dto){
        User entity = new User();
        return userRepository.save(CopyDtoToEntity(dto, entity));
    }
    private User CopyDtoToEntity(UserDTO dto, User entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setDocument(dto.getDocument());
        entity.setBirthDate(dto.getBirthDate());
        entity.setAge(dto.getBirthDate());

        if(entity.getPassword().length() < 8){
            throw new NotAcceptableParameters("Password needs to be greater than 8 characters");
        }
        if(entity.getName().isBlank() || entity.getName().isEmpty()){
            throw new NotAcceptableParameters("Name cant be null");
        }
        if(entity.getAge() > 124 || entity.getAge() < 18){
            throw new NotAcceptableParameters("Invalid Birth Date");
        }

        List<User> usersList = userRepository.findAll();
        usersList.forEach(x -> {
            if(Objects.equals(x.getEmail(), entity.getEmail())){
                throw new DataAlreadyExists("Email Already Exists");
            }else if(Objects.equals(x.getDocument(), entity.getDocument())){
                throw new DataAlreadyExists("Document Already Exists");
            }
        });

        return entity;
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
