package com.chingon.FoodStorageApp.inventory.controller;

import com.chingon.FoodStorageApp.shared.constants.Routes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = Routes.API, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class ContainerController {
    //    private final IContainerService containerService;
    //
    //    @GetMapping(Routes.Storage.BY_ID + Routes.Container.BASE)
    //    public ResponseEntity<List<ContainerResponse>> fetchContainersOfStorage(
    //            @PathVariable @Positive @StorageIdParameter Long storageId) {
    //        List<ContainerResponse> containers = containerService.getAllContainersForStorage(storageId);
    //
    //        return ResponseEntity.ok(containers);
    //    }
    //
    //    @GetMapping(Routes.Container.BY_ID)
    //    public ResponseEntity<ContainerResponse> fetchContainer(
    //            @PathVariable @Positive @ContainerIdParameter Long containerId
    //    ) {
    //        ContainerResponse requstedContainer = containerService.getContainer(containerId);
    //
    //        return ResponseEntity.ok(requstedContainer);
    //    }
    //
    //    @PostMapping(Routes.Storage.BY_ID + Routes.Container.BASE)
    //    public ResponseEntity<ContainerResponse> createContainer(
    //            @PathVariable @Positive @StorageIdParameter Long storageId,
    //            @RequestBody @Valid @ContainerRequestBody ContainerRequest request
    //    ) {
    //        ContainerResponse containerCreated = containerService.createContainer(storageId, request.name(), request.description());
    //
    //        return ResponseEntity
    //                .status(HttpStatus.CREATED)
    //                .body(containerCreated);
    //
    //    }
    //
    //    @PutMapping(Routes.Container.BY_ID)
    //    public ResponseEntity<ContainerResponse> updateContainer(
    //            @PathVariable @Positive @ContainerIdParameter Long containerId,
    //            @RequestBody @Valid @ContainerRequestBody ContainerRequest containerRequest
    //    ) {
    //        ContainerResponse updatedContainer = containerService.updateContainer(containerId, containerRequest.name(), containerRequest.description());
    //
    //        return ResponseEntity.ok(updatedContainer);
    //    }
    //
    //    @PutMapping(Routes.Container.BY_ID + Routes.Container.MOVE)
    //    public ResponseEntity<ContainerResponse> moveContainer(
    //            @PathVariable @Positive @ContainerIdParameter Long containerId,
    //            @PathVariable @Positive @Parameter(description = "new ID of storage" , required = true) Long targetStorageId
    //    ) {
    //        ContainerResponse movedContainer = containerService.updateLocation(containerId,  targetStorageId);
    //
    //        return ResponseEntity.ok(movedContainer);
    //    }
    //
    //    @DeleteMapping(Routes.Container.BY_ID)
    //    public ResponseEntity<ResponseDto> deleteContainer(
    //          @PathVariable @Positive @ContainerIdParameter Long containerId
    //    ) {
    //        containerService.deleteContainer(containerId);
    //
    //        return ResponseEntity.ok(new ResponseDto("Container with ID: " + containerId + " was deleted"));
    //    }
}
