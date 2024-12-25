package com.example.visitlyBackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = NoUser.class)
    public ResponseEntity<String> handleNoUser(NoUser nocustomer) {
        return new ResponseEntity<String>("No customer Found", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidInput.class)
    public ResponseEntity<String> handleInvalidInput(InvalidInput invalidInput) {
        return new ResponseEntity<String>("The entered input value is invalid", HttpStatus.NOT_ACCEPTABLE);
    }
}
