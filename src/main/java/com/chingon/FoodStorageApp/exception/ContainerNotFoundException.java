package com.chingon.FoodStorageApp.exception;

public class ContainerNotFoundException extends RuntimeException{
    public ContainerNotFoundException(Long id) {
        super("Container with ID: " + id + " not found!");
    }
}
