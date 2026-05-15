package com.chingon.FoodStorageApp.identity.controller;

import com.chingon.FoodStorageApp.identity.dto.HouseholdRequest;
import com.chingon.FoodStorageApp.identity.dto.HouseholdResponse;
import com.chingon.FoodStorageApp.identity.service.IHouseholdService;
import com.chingon.FoodStorageApp.inventory.dto.StorageResponse;
import com.chingon.FoodStorageApp.shared.annotation.HouseholdRequestBody;
import com.chingon.FoodStorageApp.shared.api.ErrorResponseDto;
import com.chingon.FoodStorageApp.shared.api.ResponseDto;
import com.chingon.FoodStorageApp.shared.constants.Routes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "CRUD REST APIs for Household handling in the FoodStorage Application",
        description = "CRUD REST APIs in the FoodStorage Application to CREATE, UPDATE, FETCH AND DELETE Households"
)
@RestController
@Validated
@RequestMapping(path = Routes.API + Routes.Household.BASE, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class HouseholdController {

    private final IHouseholdService householdService;

    @Operation(
            summary = "Fetch all Household REST API",
            description = "REST API to fetch all given Households"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Household Found",
                    content = @Content(schema = @Schema(implementation = StorageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Household not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    }
    )
    @GetMapping
    public ResponseEntity<List<HouseholdResponse>> fetchAllHouseholds() {
        List<HouseholdResponse> householdResponses = householdService.getCurrentUsersHouseholds();

        return ResponseEntity.ok(householdResponses);
    }

    @Operation(
            summary = "Fetch {Public_ID} Household REST API",
            description = "REST API to fetch Household with given Public ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Household Found",
                    content = @Content(schema = @Schema(implementation = StorageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Household not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    }
    )
    @GetMapping(Routes.Household.BY_ID)
    public ResponseEntity<HouseholdResponse> fetchHousehold(@PathVariable UUID publicId) {
        HouseholdResponse householdResponse = householdService.getHousehold(publicId);

        return ResponseEntity.ok(householdResponse);
    }

    @Operation(
            summary = "Create Household REST API",
            description = "REST API to create a new Household"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Household created",
                    content = @Content(schema = @Schema(implementation = StorageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HouseholdResponse> createHousehold(@Valid @RequestBody @HouseholdRequestBody HouseholdRequest householdRequest) {
        HouseholdResponse householdResponse = householdService.createHousehold(householdRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(householdResponse);
    }

    @Operation(
            summary = "Update Household REST API",
            description = "REST API to update a Household with given ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Household updated",
                    content = @Content(schema = @Schema(implementation = StorageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Household not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping(path = Routes.Household.BY_ID, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HouseholdResponse> updateHouseholdDetails(@PathVariable UUID publicId, @RequestBody @Valid @HouseholdRequestBody HouseholdRequest householdRequest) {
        HouseholdResponse updatedHouseholdResponse = householdService.updateHousehold(publicId, householdRequest);

        return ResponseEntity.ok(updatedHouseholdResponse);
    }

    @Operation(
            summary = "Delete Household REST API",
            description = "REST API to delete a Household"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Household deleted",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Household not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping(path = Routes.Household.BY_ID)
    public ResponseEntity<ResponseDto> deleteHousehold(@PathVariable UUID publicId) {
        householdService.deleteHousehold(publicId);

        return ResponseEntity.ok(new ResponseDto("Household with public ID: " + publicId.toString() + " was deleted!"));
    }

}
