package com.chingon.FoodStorageApp.exception;

public class StoredItemNotFoundException extends RuntimeException{
    public StoredItemNotFoundException(Long id) {
        super("Stored Item with ID: " + id + " not found!");
    }
}
