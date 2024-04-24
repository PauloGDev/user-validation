package com.volt.clientscrud.controllers.exceptions;

public class EntityNotFound extends RuntimeException{
    public EntityNotFound(String msg){
        super(msg);
    }
}
