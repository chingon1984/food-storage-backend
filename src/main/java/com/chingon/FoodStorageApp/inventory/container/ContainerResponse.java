package com.chingon.FoodStorageApp.inventory.container;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ContainerResponse", description = "Schema to hold Container Response Information")
public record ContainerResponse(
        @Schema(description = "ID of the container", example = "1")
        Long id,

        @Schema(description = "name of the container", example = "container #1")
        String name,

        @Schema(description = "ID of the storage, the container is part of", example = "1")
        Long storageId,

        @Schema(description = "description of the container", example = "metal container in the right corner")
        String description,

        @Schema(description = "has the container been removed?", example = "false")
        Boolean archived

) {
}
