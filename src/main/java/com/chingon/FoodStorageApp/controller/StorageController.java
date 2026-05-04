package com.chingon.FoodStorageApp.controller;

import com.chingon.FoodStorageApp.dto.StorageRequest;
import com.chingon.FoodStorageApp.dto.StorageResponse;
import com.chingon.FoodStorageApp.service.IStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/storages", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class StorageController {

   private final IStorageService storageService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<StorageResponse>> getAllStorages(@Valid @PathVariable Long userId) {
        List<StorageResponse> storages = storageService.getAllStoragesForUser(userId);

        return ResponseEntity.ok(storages);
    }

    @PostMapping
    public ResponseEntity<StorageResponse> createStorage(@RequestBody @Valid StorageRequest request) {
        StorageResponse response = storageService.createStorage(1L, request.name(), request.description());
        return ResponseEntity.ok(response);
    }
}
