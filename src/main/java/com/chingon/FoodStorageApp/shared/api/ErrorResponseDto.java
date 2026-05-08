package com.chingon.FoodStorageApp.shared.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponseDto",
        description = "Schema to hold error response information"
)
public record ErrorResponseDto(
        @Schema(
                description = "API path invoked by client"
        )
        String apiPath,

        @Schema(
                description = "Error message representing the error happened"
        )
        String errorMessage,

        @Schema(
                description = "Time representing when the error happened"
        )
        LocalDateTime errorTime
) {
}
