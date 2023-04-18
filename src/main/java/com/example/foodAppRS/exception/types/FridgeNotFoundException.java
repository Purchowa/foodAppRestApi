package com.example.foodAppRS.exception.types;

public class FridgeNotFoundException extends RuntimeException{
    public FridgeNotFoundException(String message){
        super(message);
    }
}
