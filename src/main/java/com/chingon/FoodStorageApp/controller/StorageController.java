package com.chingon.FoodStorageApp.controller;

import com.chingon.FoodStorageApp.dto.ResponseDto;
import com.chingon.FoodStorageApp.dto.StorageRequest;
import com.chingon.FoodStorageApp.dto.StorageResponse;
import com.chingon.FoodStorageApp.service.IStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/storages", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class StorageController {

   private final IStorageService storageService;

    @GetMapping("/fetch")
    public ResponseEntity<List<StorageResponse>> getAllStorages() {
        List<StorageResponse> storageResponses = storageService.getAllStoragesForUser(1L);

        return ResponseEntity.ok(storageResponses);
    }

    @GetMapping("/fetch/{storageId}")
    public ResponseEntity<StorageResponse> getStorageWithId(@Valid @PathVariable Long storageId) {
        StorageResponse storageResponse = storageService.getStorageById(storageId, 1L);

        return ResponseEntity.ok(storageResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<StorageResponse> createStorage(@RequestBody @Valid StorageRequest request) {
        StorageResponse storageResponse = storageService.createStorage(1L, request.name(), request.description());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(storageResponse);
    }

    @PutMapping("/update/{storageId}")
    public ResponseEntity<StorageResponse> updateStorage(@Valid @PathVariable Long storageId, @Valid @RequestBody StorageRequest storageRequest) {
        StorageResponse updatedStorageResponse = storageService.updateStorage(storageId, 1L, storageRequest.name(), storageRequest.description());

        return  ResponseEntity.ok(updatedStorageResponse);
    }

    @DeleteMapping("/delete/{storageId}")
    public ResponseEntity<ResponseDto> deleteStorage(@Valid @PathVariable Long storageId) {
        storageService.deleteStorage(storageId, 1L);

        return ResponseEntity.ok(new ResponseDto("200", "Storage with ID: " + storageId + " was deleted!"));
    }
}
