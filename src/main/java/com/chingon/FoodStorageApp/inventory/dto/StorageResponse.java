package com.chingon.FoodStorageApp.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "StorageResponse", description = "Schema to hold Storage Response Information")
public record StorageResponse(
        @Schema(
                description = "PUBLIC ID of storage", example = "355480ae-7c36-4c42-a51f-5172abefe013"
        )
        UUID publicId,
        @Schema(
                description = "name of the storage", example = "storage House"
        )
        String name,
        @Schema(
                description = "description of the storage", example = "storage in my house"
        )
        String description,
        @Schema(
                description = "has the storage been removed?", example = "false"
        )
        Boolean archived
) {
}
