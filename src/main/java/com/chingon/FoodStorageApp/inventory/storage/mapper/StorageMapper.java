package com.chingon.FoodStorageApp.inventory.storage.mapper;

import com.chingon.FoodStorageApp.inventory.storage.entity.Storage;
import com.chingon.FoodStorageApp.inventory.storage.dto.StorageResponse;
import org.springframework.stereotype.Component;

@Component
public final class StorageMapper {
    private StorageMapper() {}

    public static StorageResponse toResponse(Storage storage) {
        return new StorageResponse(
                storage.getId(),
                storage.getName(),
                storage.getDescription(),
                storage.getArchived()
        );
    }
}
