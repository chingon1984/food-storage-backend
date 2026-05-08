package com.chingon.FoodStorageApp.inventory.storage;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "StorageResponse", description = "Schema to hold Storage Response Information")
public record StorageResponse(
        @Schema(
                description = "ID of storage", example = "1"
        )
        Long id,
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
