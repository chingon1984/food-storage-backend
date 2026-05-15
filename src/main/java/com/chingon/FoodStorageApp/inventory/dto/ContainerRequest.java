package com.chingon.FoodStorageApp.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "ContainerRequest", description = "Schema to hold Container Request Information")
public record ContainerRequest(
    @Schema(description=  "name of the Container", example = "Container #1")
    @NotBlank(message = "name is required")
    String name,
    @Schema(description = "description of the container", example = "metal container in the right corner")
    String description
) {
}
