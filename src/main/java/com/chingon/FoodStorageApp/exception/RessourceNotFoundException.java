package com.chingon.FoodStorageApp.exception;

public class RessourceNotFoundException extends RuntimeException{
    public RessourceNotFoundException(String ressourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with: %s = %s", ressourceName, fieldName, fieldValue));
    }
}
