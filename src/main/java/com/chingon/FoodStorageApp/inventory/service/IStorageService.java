package com.chingon.FoodStorageApp.inventory.service;

import com.chingon.FoodStorageApp.inventory.entity.Storage;
import com.chingon.FoodStorageApp.inventory.dto.StorageResponse;

import java.util.List;

public interface IStorageService {
    List<StorageResponse> getAllStoragesForHousehold();
    StorageResponse getStorageResponseById(Long storageId);
    Storage getStorageById(Long storageId);
    StorageResponse createStorage(String name, String description);
    StorageResponse updateStorage(Long storageId, String name, String description);
    void deleteStorage(Long storageId);
}
