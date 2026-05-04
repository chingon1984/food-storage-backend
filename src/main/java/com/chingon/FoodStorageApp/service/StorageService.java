package com.chingon.FoodStorageApp.service;

import com.chingon.FoodStorageApp.dto.StorageResponse;
import com.chingon.FoodStorageApp.entity.Storage;
import com.chingon.FoodStorageApp.entity.User;
import com.chingon.FoodStorageApp.exeptions.StorageNotFoundException;
import com.chingon.FoodStorageApp.mapper.StorageMapper;
import com.chingon.FoodStorageApp.repository.IStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService implements IStorageService {

    private final IStorageRepository storageRepository;
    private final StorageMapper storageMapper;

    @Override
    public List<StorageResponse> getAllStoragesForUser(Long userId) {
        return storageRepository.findByUserIdAndArchivedFalse(userId)
                .stream()
                .map(storageMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StorageResponse getStorageById(Long storageId, Long userId) {
        Storage storage = storageRepository.findByIdAndUserIdAndArchivedFalse(storageId, userId)
                .orElseThrow(() -> new StorageNotFoundException(storageId));
        return storageMapper.toResponse(storage);
    }

    @Override
    @Transactional
    public StorageResponse createStorage(Long userId, String name, String description) {
        User user = new User();
        user.setId(userId);

        Storage storage = new Storage();
        storage.setName(name);
        storage.setDescription(description);
        storage.setUser(user);
        storage.setArchived(false);

        Storage savedStorage = storageRepository.save(storage);
        return storageMapper.toResponse(savedStorage);
    }

    @Override
    @Transactional
    public StorageResponse updateStorage(Long storageId, Long userId, String name, String description) {
        Storage storage = storageRepository.findByIdAndUserIdAndArchivedFalse(storageId, userId)
                .orElseThrow(() -> new StorageNotFoundException(storageId));

        storage.setName(name);
        storage.setDescription(description);

        Storage updatedStorage = storageRepository.save(storage);
        return storageMapper.toResponse(updatedStorage);
    }

    @Override
    @Transactional
    public void deleteStorage(Long storageId, Long userId) {
        Storage storage = storageRepository.findByIdAndUserIdAndArchivedFalse(storageId, userId)
                .orElseThrow(() -> new StorageNotFoundException(storageId));

        storage.setArchived(true);
        storageRepository.save(storage);
    }
}
