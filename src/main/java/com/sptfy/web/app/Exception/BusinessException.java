package com.sptfy.web.app.Exception;

public class BusinessException extends Exception {

    private String message;

    public BusinessException(String message){

        this.message = message;
    }

    public String getMessage(){

        return message;
    }
}
