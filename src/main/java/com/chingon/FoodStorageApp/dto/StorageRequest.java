package com.chingon.FoodStorageApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "StorageRequest", description = "Schema to hold Storage Request Information")
public record StorageRequest(
        @Schema(description=  "name of the Storage", example = "StorageHome")
        @NotBlank(message = "Name is required")
        String name,
        @Schema(description=  "description of the Storage", example = "storage in my house")
        String description) {
}
