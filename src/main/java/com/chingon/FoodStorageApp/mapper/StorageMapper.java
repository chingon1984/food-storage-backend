package com.chingon.FoodStorageApp.mapper;

import com.chingon.FoodStorageApp.dto.StorageResponse;
import com.chingon.FoodStorageApp.entity.Storage;
import org.springframework.stereotype.Component;

@Component
public class StorageMapper {

    public StorageResponse toResponse(Storage storage) {
        return new StorageResponse(
                storage.getId(),
                storage.getName(),
                storage.getDescription(),
                storage.getArchived()
        );
    }
}
