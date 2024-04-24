package com.volt.clientscrud.controllers.exceptions;

import com.volt.clientscrud.services.exceptions.DefaultException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<DefaultException> entityNotFound(EntityNotFound e, HttpServletRequest request){
        DefaultException error = new DefaultException();
        error.setTimeStamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Resource Not Found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataAlreadyExists.class)
    public ResponseEntity<DefaultException> dataAlreadysExist(DataAlreadyExists e, HttpServletRequest request){
        DefaultException error = new DefaultException();
        error.setTimeStamp(Instant.now());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setError("Data already exists");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
    @ExceptionHandler(NotAcceptableParameters.class)
    public ResponseEntity<DefaultException> notAcceptableParameters(NotAcceptableParameters e, HttpServletRequest request){
        DefaultException error = new DefaultException();
        error.setTimeStamp(Instant.now());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setError("Not acceptable parameters");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
