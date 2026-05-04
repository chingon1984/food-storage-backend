package com.chingon.FoodStorageApp.service;

import com.chingon.FoodStorageApp.dto.StorageResponse;

import java.util.List;

public interface IStorageService {
    List<StorageResponse> getAllStoragesForUser(Long userId);
    StorageResponse getStorageById(Long storageId, Long userId);
    StorageResponse createStorage(Long userId, String name, String description);
    StorageResponse updateStorage(Long storageId, Long userId, String name, String description);
    void deleteStorage(Long storageId, Long userId);
}
