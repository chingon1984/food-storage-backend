package com.chingon.FoodStorageApp.dto;

import jakarta.validation.constraints.NotBlank;

public record StorageRequest(
        @NotBlank(message = "Name is required")
        String name,
        String description) {
}
