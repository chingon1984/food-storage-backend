package com.chingon.FoodStorageApp.inventory.storage.service;

import com.chingon.FoodStorageApp.inventory.storage.entity.Storage;
import com.chingon.FoodStorageApp.inventory.storage.dto.StorageResponse;

import java.util.List;

public interface IStorageService {
    List<StorageResponse> getAllStoragesForUser(Long userId);
    StorageResponse getStorageResponseById(Long storageId, Long userId);
    Storage getStorageById(Long storageId, Long userId);
    StorageResponse createStorage(Long userId, String name, String description);
    StorageResponse updateStorage(Long storageId, Long userId, String name, String description);
    void deleteStorage(Long storageId, Long userId);
}
