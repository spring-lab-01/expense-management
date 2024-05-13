package com.hk.prj.expenses360.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){

    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
