package com.chingon.FoodStorageApp.identity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


@Schema(name = "HouseholdRequest", description = "Schema to hold Household Request Information")
public record HouseholdRequest(
        @Schema(description = "name of the Household", example = "Family Huber")
        @NotBlank(message = "name is required")
        String name,
        @Schema(description = "description of the Household", example = "Household of family Huber")
        String description
) {
}
