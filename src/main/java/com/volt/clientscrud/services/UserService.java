package com.volt.clientscrud.services;

import com.volt.clientscrud.controllers.exceptions.DataAlreadyExists;
import com.volt.clientscrud.controllers.exceptions.EntityNotFound;
import com.volt.clientscrud.controllers.exceptions.NotAcceptableParameters;
import com.volt.clientscrud.dtos.UserDTO;
import com.volt.clientscrud.models.User;
import com.volt.clientscrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    private User CopyDtoToEntity(UserDTO dto, User entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setDocument(dto.getDocument());
        entity.setBirthDate(dto.getBirthDate());
        int age = dto.getAge();

        if(!checkDocument(entity.getDocument()) || (!(entity.getDocument().length() == 11))){
            throw new NotAcceptableParameters("Invalid Document");
        }
        if(entity.getPassword().length() < 8){
            throw new NotAcceptableParameters("Password needs to be greater than 8 characters");
        }
        if(entity.getName().isBlank() || entity.getName().isEmpty()){
            throw new NotAcceptableParameters("Name cant be null");
        }
        if(age > 124 || age < 18){
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
    Boolean checkDocument(String document){
        int checks = 0;
        for(int i = 0; i < 11; i++){
            for(int j = 0; j <= 9; j++){
                if(document.substring(j, j + 1).equals(Integer.toString(i))){
                    checks++;
                }
            }
            if(checks >= 11){
                throw new NotAcceptableParameters("Invalid Document");
            }else{
                checks = 0;
            }
        }
        int checkResult = 0;
        int index = 0;
        for(int i = 10; i > 1; i--){
            index++;
            try{
                checkResult += i * Integer.parseInt(document.substring(index - 1, index));
            }catch (NotAcceptableParameters e){
                throw new NotAcceptableParameters("Invalid Document");
            }
        }

        checkResult = checkResult % 11;
        System.out.println(checkResult);
        checkResult = Math.abs(checkResult - 11);
        checkResult = checkResult > 9 ? 0 : checkResult;
        String documentValidator = "" + (checkResult);
        checkResult = 0;
        index = 0;

        for(int i = 11; i > 1; i--){
            index++;
            try{
                checkResult += i * Integer.parseInt(document.substring(index - 1, index));
            }catch (NotAcceptableParameters e){
                throw new NotAcceptableParameters("Invalid Document");
            }
        }
        index = 0;
        checkResult = checkResult % 11;
        System.out.println(checkResult);
        checkResult = Math.abs(checkResult - 11);
        checkResult = checkResult > 9 ? 0 : checkResult;
        documentValidator += (checkResult);

        return documentValidator.equals(document.substring(9, 11));
    }
}
