package com.chingon.FoodStorageApp.inventory.container;

import com.chingon.FoodStorageApp.inventory.storage.StorageResponse;
import com.chingon.FoodStorageApp.shared.constants.FoodStorageConstants;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping(path = FoodStorageConstants.API_PATH + FoodStorageConstants.STORAGES_PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class ContainerController {

    private final IContainerService containerService;

    @GetMapping("/{storageId}/containers")
    public ResponseEntity<List<ContainerResponse>> fetchContainers(@PathVariable @Positive @Parameter(description = "ID of the container's storage to fetch", example = "1") Long storageId) {
        List<ContainerResponse> containers = containerService.getAllContainersForStorage(storageId, 1L);

        return ResponseEntity.ok(containers);
    }
}
