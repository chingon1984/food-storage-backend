package com.chingon.FoodStorageApp.inventory.service;

import com.chingon.FoodStorageApp.inventory.entity.Storage;
import com.chingon.FoodStorageApp.inventory.mapper.StorageMapper;
import com.chingon.FoodStorageApp.inventory.dto.StorageResponse;
import com.chingon.FoodStorageApp.inventory.repository.StorageRepository;
import com.chingon.FoodStorageApp.identity.service.CurrentUserService;
import com.chingon.FoodStorageApp.identity.entity.User;
import com.chingon.FoodStorageApp.shared.exception.RessourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService implements IStorageService {

    private final StorageRepository storageRepository;
    private final CurrentUserService currentUserService;

    @Override
    public List<StorageResponse> getAllStoragesForHousehold() {
        Long userId = getCurrentUserId();
        return storageRepository.findByHouseholdIdAndArchivedFalse(userId)
                .stream()
                .map(StorageMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StorageResponse getStorageResponseById(Long storageId) {
        Long userId = getCurrentUserId();
        Storage storage = storageRepository.findByIdAndHouseholdIdAndArchivedFalse(storageId, userId)
                .orElseThrow(() -> new RessourceNotFoundException("Storage", "ID", storageId.toString()));
        return StorageMapper.toResponse(storage);
    }

    @Override
    public Storage getStorageById(Long storageId) {
        Long userId = getCurrentUserId();
        return storageRepository.findByIdAndHouseholdIdAndArchivedFalse(storageId, userId)
                .orElseThrow(() -> new RessourceNotFoundException("Storage", "ID", storageId.toString()));
    }

    @Override
    @Transactional
    public StorageResponse createStorage(String name, String description) {
        Long userId = getCurrentUserId();
        User user = new User();
        user.setId(userId);

        Storage storage = new Storage();
        storage.setName(name);
        storage.setDescription(description);
//        storage.setUser(user);
        storage.setArchived(false);

        Storage savedStorage = storageRepository.save(storage);
        return StorageMapper.toResponse(savedStorage);
    }

    @Override
    @Transactional
    public StorageResponse updateStorage(Long storageId, String name, String description) {
        Long userId = getCurrentUserId();
        Storage storage = storageRepository.findByIdAndHouseholdIdAndArchivedFalse(storageId, userId)
                .orElseThrow(() -> new RessourceNotFoundException("Storage", "ID", storageId.toString()));

        storage.setName(name);
        storage.setDescription(description);

        return StorageMapper.toResponse(storage);
    }

    @Override
    @Transactional
    public void deleteStorage(Long storageId) {
        Long userId = getCurrentUserId();
        Storage storage = storageRepository.findByIdAndHouseholdIdAndArchivedFalse(storageId, userId)
                .orElseThrow(() -> new RessourceNotFoundException("Storage", "ID", storageId.toString()));

        storage.setArchived(true);
    }

    private Long getCurrentUserId() {
        return currentUserService.getCurrentUser().getId();
    }
}
