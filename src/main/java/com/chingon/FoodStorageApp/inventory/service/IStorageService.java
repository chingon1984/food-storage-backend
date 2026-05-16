package com.chingon.FoodStorageApp.inventory.service;

import com.chingon.FoodStorageApp.inventory.dto.StorageRequest;
import com.chingon.FoodStorageApp.inventory.dto.StorageResponse;

import java.util.List;
import java.util.UUID;

public interface IStorageService {
    List<StorageResponse> getAllStorages(UUID publicId);

    StorageResponse getStorage(Long storageId);

    StorageResponse createStorage(UUID publicId, StorageRequest newStorage);

    StorageResponse updateStorage(Long storageId, StorageRequest newStorage);

    void deleteStorage(Long storageId);
}
