package com.chingon.FoodStorageApp.inventory.service;

import com.chingon.FoodStorageApp.identity.entity.Household;
import com.chingon.FoodStorageApp.identity.service.HouseholdAccessService;
import com.chingon.FoodStorageApp.inventory.dto.StorageRequest;
import com.chingon.FoodStorageApp.inventory.dto.StorageResponse;
import com.chingon.FoodStorageApp.inventory.entity.Storage;
import com.chingon.FoodStorageApp.inventory.mapper.StorageMapper;
import com.chingon.FoodStorageApp.inventory.repository.StorageRepository;
import com.chingon.FoodStorageApp.shared.exception.RessourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService implements IStorageService {

    private final StorageRepository storageRepository;
    private final HouseholdAccessService accessService;

    @Override
    public List<StorageResponse> getAllStoragesByHousehold(UUID publicId) {
        Household requestedHousehold = accessService.getHouseholdByPublicId(publicId);
        return getStoragesByHouseholdId(requestedHousehold.getId());
    }


    private List<StorageResponse> getStoragesByHouseholdId(Long householdId) {
        return storageRepository.findByHouseholdIdAndArchivedFalse(householdId)
                .stream()
                .map(StorageMapper::toResponse)
                .toList();
    }


    @Override
    public StorageResponse getStorage(UUID publicId) {
        Storage requestedStorage = getStorageByIdAccessibleForCurrentUser(publicId);

        return StorageMapper.toResponse(requestedStorage);
    }

    private Storage getStorageByIdAccessibleForCurrentUser(UUID publicId) {
        Storage storage = storageRepository.findByPublicIdAndArchivedFalse(publicId)
                .orElseThrow(() -> new RessourceNotFoundException("Storage", "Public ID", publicId.toString()));

        accessService.checkHouseholdPermissions(storage.getHousehold());

        return storage;
    }

    @Override
    public StorageResponse createStorageAtHousehold(UUID publicId, StorageRequest newStorage) {
        Household household = accessService.getHouseholdByPublicId(publicId);

        return createStorageInHousehold(household, newStorage);
    }

    private StorageResponse createStorageInHousehold(Household household, StorageRequest newStorage) {
        Storage storage = new Storage();
        storage.setName(newStorage.name());
        storage.setDescription(newStorage.description());
        storage.setHousehold(household);

        Storage createdStorage = storageRepository.save(storage);

        return StorageMapper.toResponse(createdStorage);
    }

    @Transactional
    @Override
    public StorageResponse updateStorage(UUID publicId, StorageRequest newStorage) {
        Storage updateableStorage = getStorageByIdAccessibleForCurrentUser(publicId);

        updateableStorage.setName(newStorage.name());
        updateableStorage.setDescription(newStorage.description());

        return StorageMapper.toResponse(updateableStorage);
    }


    @Override
    @Transactional
    public void deleteStorage(UUID publicId) {
        Storage deletableStorage = getStorageByIdAccessibleForCurrentUser(publicId);
        deletableStorage.setArchived(true);
    }
}
