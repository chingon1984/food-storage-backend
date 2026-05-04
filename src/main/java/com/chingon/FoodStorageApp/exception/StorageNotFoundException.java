package com.chingon.FoodStorageApp.exception;

public class StorageNotFoundException extends RuntimeException {
        public StorageNotFoundException(Long storageId) {
            super("Storage with ID " + storageId + " not found.");
        }
}
