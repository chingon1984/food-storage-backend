package com.chingon.FoodStorageApp.inventory.storage;

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
