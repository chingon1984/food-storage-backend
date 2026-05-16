package com.chingon.FoodStorageApp.inventory.service;

import com.chingon.FoodStorageApp.inventory.dto.StorageRequest;
import com.chingon.FoodStorageApp.inventory.dto.StorageResponse;

import java.util.List;
import java.util.UUID;

public interface IStorageService {
    List<StorageResponse> getAllStoragesByHousehold(UUID publicId);

    StorageResponse getStorage(UUID publicId);

    StorageResponse createStorageAtHousehold(UUID publicId, StorageRequest newStorage);

    StorageResponse updateStorage(UUID publicId, StorageRequest newStorage);

    void deleteStorage(UUID publicId);
}
