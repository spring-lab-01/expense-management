package com.hk.prj.expense360oauth.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){

    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
