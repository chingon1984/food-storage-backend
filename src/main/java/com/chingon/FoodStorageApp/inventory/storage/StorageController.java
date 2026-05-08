package com.chingon.FoodStorageApp.inventory.storage;

import com.chingon.FoodStorageApp.shared.annotation.ContainerRequestBody;
import com.chingon.FoodStorageApp.shared.annotation.StorageIdParameter;
import com.chingon.FoodStorageApp.shared.api.ErrorResponseDto;
import com.chingon.FoodStorageApp.shared.api.ResponseDto;
import com.chingon.FoodStorageApp.shared.constants.Routes;
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
@RequestMapping(path = Routes.API + Routes.Storage.BASE, produces = {MediaType.APPLICATION_JSON_VALUE})
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
    @GetMapping()
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
    @GetMapping(Routes.Storage.BY_ID)
    public ResponseEntity<StorageResponse> getStorageWithId(
            @PathVariable @Positive @StorageIdParameter Long storageId) {
        StorageResponse storageResponse = storageService.getStorageResponseById(storageId, 1L);

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
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StorageResponse> createStorage(
            @RequestBody
            @ContainerRequestBody
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
    @PutMapping(Routes.Storage.BY_ID)
    public ResponseEntity<StorageResponse> updateStorage( @PathVariable @Positive  @StorageIdParameter Long storageId, @Valid @RequestBody StorageRequest storageRequest) {
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
    @DeleteMapping(Routes.Storage.BY_ID)
    public ResponseEntity<ResponseDto> deleteStorage(@Valid @Positive @StorageIdParameter Long storageId) {
        storageService.deleteStorage(storageId, 1L);

        return ResponseEntity.ok(new ResponseDto("Storage with ID: " + storageId + " was deleted!"));
    }
}
