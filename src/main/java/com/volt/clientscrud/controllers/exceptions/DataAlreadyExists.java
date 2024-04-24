package com.volt.clientscrud.controllers.exceptions;

public class DataAlreadyExists extends RuntimeException{
    public DataAlreadyExists(String msg) {
        super(msg);
    }
}
