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
@RequestMapping(path = Routes.API , produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class ContainerController {
    private final IContainerService containerService;

    @GetMapping(Routes.Storage.BY_ID + Routes.Container.BASE)
    public ResponseEntity<List<ContainerResponse>> fetchContainersOfStorage(
            @PathVariable @Positive @StorageIdParameter Long storageId) {
        List<ContainerResponse> containers = containerService.getAllContainersForStorage(storageId, 1L);

        return ResponseEntity.ok(containers);
    }

    @GetMapping(Routes.Container.BY_ID)
    public ResponseEntity<ContainerResponse> fetchContainer(
            @PathVariable @Positive @ContainerIdParameter Long containerId
    ) {
        ContainerResponse requstedContainer = containerService.getContainer(containerId, 1L);

        return ResponseEntity.ok(requstedContainer);
    }

    @PostMapping(Routes.Storage.BY_ID + Routes.Container.BASE)
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
            @PathVariable @Positive @ContainerIdParameter Long containerId,
            @RequestBody @Valid @ContainerRequestBody ContainerRequest containerRequest
    ) {
        ContainerResponse updatedContainer = containerService.updateContainer(containerId, 1L, containerRequest.name(), containerRequest.description());

        return ResponseEntity.ok(updatedContainer);
    }

    @PutMapping(Routes.Container.BY_ID + Routes.Container.MOVE)
    public ResponseEntity<ContainerResponse> moveContainer(
            @PathVariable @Positive @ContainerIdParameter Long containerId,
            @PathVariable @Positive @Parameter(description = "new ID of storage" , required = true) Long targetStorageId
    ) {
        ContainerResponse movedContainer = containerService.updateLocation(containerId,  targetStorageId, 1L);

        return ResponseEntity.ok(movedContainer);
    }

    @DeleteMapping(Routes.Container.BY_ID)
    public ResponseEntity<ResponseDto> deleteContainer(
          @PathVariable @Positive @ContainerIdParameter Long containerId
    ) {
        containerService.deleteContainer(containerId, 1L);

        return ResponseEntity.ok(new ResponseDto("Container with ID: " + containerId + " was deleted"));
    }
}
