package com.chingon.FoodStorageApp.inventory.container;

import com.chingon.FoodStorageApp.shared.annotation.ContainerIdParameter;
import com.chingon.FoodStorageApp.shared.annotation.ContainerRequestBody;
import com.chingon.FoodStorageApp.shared.annotation.StorageIdParameter;
import com.chingon.FoodStorageApp.shared.api.ResponseDto;
import com.chingon.FoodStorageApp.shared.constants.Routes;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping(path = Routes.API + Routes.Storage.BY_ID, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class ContainerController {
    private final IContainerService containerService;

    @GetMapping(Routes.Container.BASE)
    public ResponseEntity<List<ContainerResponse>> fetchAllContainersOfStorage(
            @PathVariable @Positive @StorageIdParameter Long storageId) {
        List<ContainerResponse> containers = containerService.getAllContainersForStorage(storageId, 1L);

        return ResponseEntity.ok(containers);
    }

    @GetMapping(Routes.Container.BY_ID)
    public ResponseEntity<ContainerResponse> fetchContainer(
            @PathVariable @Positive @StorageIdParameter Long storageId,
            @PathVariable @Positive @ContainerIdParameter Long containerId
    ) {
        ContainerResponse requstedContainer = containerService.getContainer(storageId, containerId, 1L);

        return ResponseEntity.ok(requstedContainer);
    }

    @PostMapping(Routes.Container.BASE)
    public ResponseEntity<ContainerResponse> createContainer(
            @PathVariable @Positive @StorageIdParameter Long storageId,
            @RequestBody @Valid @ContainerRequestBody ContainerRequest request
    ) {
        ContainerResponse containerCreated = containerService.createContainer(storageId, 1L, request.name(), request.description());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(containerCreated);

    }

    @PutMapping(Routes.Container.BY_ID)
    public ResponseEntity<ContainerResponse> updateContainer(
            @PathVariable @Positive @StorageIdParameter Long storageId,
            @PathVariable @Positive @ContainerIdParameter Long containerId,
            @RequestBody @Valid @ContainerRequestBody ContainerRequest containerRequest
    ) {
        ContainerResponse updatedContainer = containerService.updateContainer(containerId, storageId, 1L,containerRequest.name(), containerRequest.description());

        return ResponseEntity.ok(updatedContainer);
    }

    @PutMapping(Routes.Container.BY_ID + Routes.Container.MOVE)
    public ResponseEntity<ContainerResponse> updateLocation(
            @PathVariable @Positive @StorageIdParameter Long storageId,
            @PathVariable @Positive @ContainerIdParameter Long containerId,
            @PathVariable @Positive @Parameter(description = "new ID of storage" , required = true) Long newStorageId
    ) {
        ContainerResponse movedContainer = containerService.updateLocation(containerId, storageId, newStorageId, 1L);

        return ResponseEntity.ok(movedContainer);
    }

    @DeleteMapping(Routes.Container.BY_ID)
    public ResponseEntity<ResponseDto> deleteContainer(
          @PathVariable @Positive @StorageIdParameter Long storageId,
          @PathVariable @Positive @ContainerIdParameter Long containerId
    ) {
        containerService.deleteContainer(containerId, storageId, 1L);

        return ResponseEntity.ok(new ResponseDto("Container with ID: " + containerId + " was deleted from storage: " + storageId));
    }
}
