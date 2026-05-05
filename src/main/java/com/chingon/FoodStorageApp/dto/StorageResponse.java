package com.chingon.FoodStorageApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Schema(name = "StorageResponse", description = "Schema to hold Storage Response Information")
public record StorageResponse(
        @Schema(
                description = "ID of storage", example = "1"
        )
        Long id,
        @Schema(
                description = "name of the storage", example = "storageHouse"
        )
        String name,
        @Schema(
                description = "description of the storage", example = "storage in my house"
        )
        String description,
        @Schema(
                description = "is the storage still in use?", example = "false"
        )
        Boolean archived
) {
}
