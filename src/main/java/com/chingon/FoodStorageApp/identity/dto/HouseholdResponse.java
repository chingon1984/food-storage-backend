package com.chingon.FoodStorageApp.identity.dto;

import com.chingon.FoodStorageApp.identity.entity.Household_Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record HouseholdResponse(
        @Schema(description = "public ID of the Household", example = "")
        UUID publicId,
        @Schema(description = "name of the Household", example = "Family Huber")
        String name,
        @Schema(description = "description of the Household", example = "Household of family Huber")
        String description,
        @Schema(
                description = "has the storage been removed?", example = "false"
        )
        Boolean archived,
        @Schema(
                description = "Household role of the user", example = "MEMBER"
        )
        Household_Role role
) {
}
