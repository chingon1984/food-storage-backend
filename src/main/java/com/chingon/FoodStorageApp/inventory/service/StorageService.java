package com.chingon.FoodStorageApp.inventory.service;

import com.chingon.FoodStorageApp.identity.entity.Household;
import com.chingon.FoodStorageApp.identity.repository.HouseholdRepository;
import com.chingon.FoodStorageApp.identity.service.HouseholdAccessService;
import com.chingon.FoodStorageApp.identity.service.ICurrentUserService;
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
    private final ICurrentUserService currentUserService;
    private final HouseholdRepository householdRepository;
    private final HouseholdAccessService accessService;

    @Override
    public List<StorageResponse> getAllStorages(UUID publicId) {
        Household requestedHousehold = getHouseholdByPublicIdAccessibleForCurrentUser(publicId);
        return getStoragesByHouseholdId(requestedHousehold.getId());
    }

    private Household getHouseholdByPublicIdAccessibleForCurrentUser(UUID publicId) {
        Household requestedHousehold = getHouseholdByPublicId(publicId);
        checkHouseholdPermissions(requestedHousehold);

        return requestedHousehold;
    }

    private List<StorageResponse> getStoragesByHouseholdId(Long householdId) {
        return storageRepository.findByHouseholdIdAndArchivedFalse(householdId)
                .stream()
                .map(StorageMapper::toResponse)
                .toList();
    }

    private Household getHouseholdByPublicId(UUID publicId) {
        return householdRepository.findByPublicIdAndArchivedFalse(publicId)
                .orElseThrow(() -> new RessourceNotFoundException("Household", "PublicId", publicId.toString()));
    }

    private void checkHouseholdPermissions(Household household) {
        boolean isMember = accessService.isCurrentUserMemberOfHousehold(household.getId());

        if (!isMember) {
            throw new RessourceNotFoundException("Household", "PublicId", household.getPublicId().toString());
        }

    }

    @Override
    public StorageResponse getStorage(Long storageId) {
        Storage requestedStorage = getStorageByIdAccessibleForCurrentUser(storageId);

        return StorageMapper.toResponse(requestedStorage);
    }

    private Storage getStorageByIdAccessibleForCurrentUser(Long storageId) {
        Storage storage = storageRepository.findByIdAndArchivedFalse(storageId)
                .orElseThrow(() -> new RessourceNotFoundException("Storage", "ID", storageId.toString()));

        checkHouseholdPermissions(storage.getHousehold());

        return storage;
    }

    @Override
    public StorageResponse createStorage(UUID publicId, StorageRequest newStorage) {
        Household household = getHouseholdByPublicIdAccessibleForCurrentUser(publicId);

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
    public StorageResponse updateStorage(Long storageId, StorageRequest newStorage) {
        Storage updateableStorage = getStorageByIdAccessibleForCurrentUser(storageId);

        updateableStorage.setName(newStorage.name());
        updateableStorage.setDescription(newStorage.description());

        return StorageMapper.toResponse(updateableStorage);
    }


    @Override
    @Transactional
    public void deleteStorage(Long storageId) {
        Storage deletableStorage = getStorageByIdAccessibleForCurrentUser(storageId);
        deletableStorage.setArchived(true);
    }
}
