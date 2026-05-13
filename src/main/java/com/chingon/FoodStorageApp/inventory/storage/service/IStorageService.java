package com.chingon.FoodStorageApp.inventory.storage.service;

import com.chingon.FoodStorageApp.inventory.storage.entity.Storage;
import com.chingon.FoodStorageApp.inventory.storage.dto.StorageResponse;

import java.util.List;

public interface IStorageService {
    List<StorageResponse> getAllStoragesForUser();
    StorageResponse getStorageResponseById(Long storageId);
    Storage getStorageById(Long storageId);
    StorageResponse createStorage(String name, String description);
    StorageResponse updateStorage(Long storageId, String name, String description);
    void deleteStorage(Long storageId);
}
