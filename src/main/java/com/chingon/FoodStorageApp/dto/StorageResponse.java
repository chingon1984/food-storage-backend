package com.chingon.FoodStorageApp.dto;

public record StorageResponse(
        Long id,
        String name,
        String description,
        Boolean archived
) {
}
