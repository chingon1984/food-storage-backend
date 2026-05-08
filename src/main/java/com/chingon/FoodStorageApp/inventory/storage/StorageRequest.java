package com.chingon.FoodStorageApp.inventory.storage;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "StorageRequest", description = "Schema to hold Storage Request Information")
public record StorageRequest(
        @Schema(description=  "name of the Storage", example = "Home Storage")
        @NotBlank(message = "name is required")
        String name,
        @Schema(description=  "description of the Storage", example = "Storage at home")
        String description) {
}
