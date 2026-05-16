package com.chingon.FoodStorageApp.inventory.controller;

import com.chingon.FoodStorageApp.inventory.dto.StorageRequest;
import com.chingon.FoodStorageApp.inventory.dto.StorageResponse;
import com.chingon.FoodStorageApp.inventory.service.IStorageService;
import com.chingon.FoodStorageApp.shared.annotation.ContainerRequestBody;
import com.chingon.FoodStorageApp.shared.annotation.StoragePublicIdParameter;
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
        name = "CRUD REST APIs for Storage handling in the FoodStorage Application",
        description = "CRUD REST APIs in the FoodStorage Application to CREATE, UPDATE, FETCH AND DELETE storages"
)
@RestController
@Validated
@RequestMapping(path = Routes.API, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class StorageController {

    private final IStorageService storageService;

    @Operation(
            summary = "Fetch all Storages REST API",
            description = "REST API to fetch all storages for given household"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Storage Found",
                    content = @Content(schema = @Schema(implementation = StorageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Storage not found",
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
    @GetMapping(path = Routes.Household.BASE + Routes.Household.BY_ID + Routes.Storage.BASE)
    public ResponseEntity<List<StorageResponse>> getAllStoragesForHousehold(@PathVariable UUID publicId) {
        List<StorageResponse> storageResponses = storageService.getAllStoragesByHousehold(publicId);

        return ResponseEntity.ok(storageResponses);
    }


    @Operation(
            summary = "Fetch {ID} Storage REST API",
            description = "REST API to fetch storage with given ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Storage Found",
                    content = @Content(schema = @Schema(implementation = StorageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Storage not found",
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
    @GetMapping(Routes.Storage.BASE + Routes.Storage.BY_ID)
    public ResponseEntity<StorageResponse> getStorage(
            @PathVariable @StoragePublicIdParameter UUID publicId) {
        StorageResponse storageResponse = storageService.getStorage(publicId);

        return ResponseEntity.ok(storageResponse);
    }

    @Operation(
            summary = "Create Storage REST API",
            description = "REST API to create a new storage in household"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Storage created",
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
    @PostMapping(path = Routes.Household.BASE + Routes.Household.BY_ID + Routes.Storage.BASE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StorageResponse> createStorage(
            @PathVariable UUID publicId,
            @RequestBody @ContainerRequestBody @Valid StorageRequest request) {
        StorageResponse storageResponse = storageService.createStorageAtHousehold(publicId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(storageResponse);
    }

    @Operation(
            summary = "Update Storage REST API",
            description = "REST API to update a storage with given ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Storage updated",
                    content = @Content(schema = @Schema(implementation = StorageResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Storage not found",
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
    @PutMapping(Routes.Storage.BASE + Routes.Storage.BY_ID)
    public ResponseEntity<StorageResponse> updateStorage(
            @PathVariable @StoragePublicIdParameter UUID publicId,
            @Valid @RequestBody StorageRequest storageRequest) {
        StorageResponse updatedStorageResponse = storageService.updateStorage(publicId, storageRequest);

        return ResponseEntity.ok(updatedStorageResponse);
    }

    @Operation(
            summary = "Delete Storage REST API",
            description = "REST API to delete a storage"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Storage deleted",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Storage not found",
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
    @DeleteMapping(Routes.Storage.BASE + Routes.Storage.BY_ID)
    public ResponseEntity<ResponseDto> deleteStorage(@PathVariable @StoragePublicIdParameter UUID publicId) {
        storageService.deleteStorage(publicId);

        return ResponseEntity.ok(new ResponseDto("Storage with ID: " + publicId.toString() + " was deleted!"));
    }
}
