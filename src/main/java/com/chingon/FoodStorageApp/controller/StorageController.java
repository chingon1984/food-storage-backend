package com.chingon.FoodStorageApp.controller;

import com.chingon.FoodStorageApp.dto.ErrorResponseDto;
import com.chingon.FoodStorageApp.dto.ResponseDto;
import com.chingon.FoodStorageApp.dto.StorageRequest;
import com.chingon.FoodStorageApp.dto.StorageResponse;
import com.chingon.FoodStorageApp.service.IStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Storage handling in the FoodStorage Application",
        description = "CRUD REST APIs in the FoodStorage Application to CREATE, UPDATE, FETCH AND DELETE storages"
)
@RestController
@Validated
@RequestMapping(path = "/api/v1/storages", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class StorageController {

    private final IStorageService storageService;

    @Operation(
            summary = "Fetch all Storage REST API",
            description = "REST API to fetch all given storages"
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
    @GetMapping("/fetch")
    public ResponseEntity<List<StorageResponse>> getAllStorages() {
        List<StorageResponse> storageResponses = storageService.getAllStoragesForUser(1L);

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
    @GetMapping("/fetch/{storageId}")
    public ResponseEntity<StorageResponse> getStorageWithId(@PathVariable @Positive @Parameter(description = "ID of the storage to fetch", example = "1", required = true) Long storageId) {
        StorageResponse storageResponse = storageService.getStorageById(storageId, 1L);

        return ResponseEntity.ok(storageResponse);
    }

    @Operation(
            summary = "Create Storage REST API",
            description = "REST API to create a new storage"

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
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StorageResponse> createStorage(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Storage Request Dto", required = true)
            @Valid StorageRequest request) {
        StorageResponse storageResponse = storageService.createStorage(1L, request.name(), request.description());

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
    @PutMapping("/update/{storageId}")
    public ResponseEntity<StorageResponse> updateStorage( @PathVariable @Positive  @Parameter(description = "ID of the storage to update", required = true) Long storageId, @Valid @RequestBody StorageRequest storageRequest) {
        StorageResponse updatedStorageResponse = storageService.updateStorage(storageId, 1L, storageRequest.name(), storageRequest.description());

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
    @DeleteMapping("/delete/{storageId}")
    public ResponseEntity<ResponseDto> deleteStorage(@Valid @Positive @PathVariable @Parameter(description = "ID of the storage to delete", required = true) Long storageId) {
        storageService.deleteStorage(storageId, 1L);

        return ResponseEntity.ok(new ResponseDto("Storage with ID: " + storageId + " was deleted!"));
    }
}
