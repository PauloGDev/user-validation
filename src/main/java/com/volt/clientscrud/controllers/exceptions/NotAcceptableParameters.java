package com.volt.clientscrud.controllers.exceptions;

public class NotAcceptableParameters extends RuntimeException{
    public NotAcceptableParameters(String message) {
        super(message);
    }
}
