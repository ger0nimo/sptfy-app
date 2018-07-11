package com.sptfy.web.app.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleException(BusinessException e){

        ErrorMessage error = new ErrorMessage(500, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
