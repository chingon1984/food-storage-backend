package com.chingon.FoodStorageApp.inventory.container.service;

import com.chingon.FoodStorageApp.inventory.container.dto.ContainerResponse;
import com.chingon.FoodStorageApp.inventory.container.repository.ContainerRepository;
import com.chingon.FoodStorageApp.inventory.container.entity.Container;
import com.chingon.FoodStorageApp.inventory.container.mapper.ContainerMapper;
import com.chingon.FoodStorageApp.inventory.storage.entity.Storage;
import com.chingon.FoodStorageApp.inventory.storage.repository.StorageRepository;
import com.chingon.FoodStorageApp.inventory.storage.service.IStorageService;
import com.chingon.FoodStorageApp.shared.exception.RessourceNotFoundException;
import com.chingon.FoodStorageApp.user.CurrentUserService;
import com.chingon.FoodStorageApp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContainerService implements IContainerService {
    private final ContainerRepository containerRepository;
    private final StorageRepository storageRepository;
    private final IStorageService storageService;
    private final CurrentUserService currentUserService;

    @Override
    public List<ContainerResponse> getAllContainersForStorage(Long storageId) {
        Long userId = getCurrentUserId();
        return containerRepository.findActiveContainersByStorageId(storageId, userId)
                .stream()
                .map(ContainerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContainerResponse> getAllContainers() {
        Long userId = getCurrentUserId();
        return containerRepository.findAllActiveContainersByUserId(userId)
                .stream()
                .map(ContainerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ContainerResponse getContainer(Long containerId) {
        Long userId = getCurrentUserId();
        Container requestedContainer = containerRepository.findActiveContainerById(containerId,  userId)
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        return ContainerMapper.toResponse(requestedContainer);
    }


    @Override
    public ContainerResponse createContainer(Long storageId, String name, String description) {
        Storage storageToStoreContainerTo = getStorage(storageId);

        Container container = new Container();
        container.setName(name);
        container.setDescription(description);
        container.setStorage(storageToStoreContainerTo);

        Container savedContainer = containerRepository.save(container);

        return ContainerMapper.toResponse(savedContainer);
    }


    @Transactional
    @Override
    public ContainerResponse updateContainer(Long containerId, String name, String description) {
        Long userId = getCurrentUserId();
        Container containerToUpdate = containerRepository.findActiveContainerById(containerId, userId)
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        containerToUpdate.setName(name);
        containerToUpdate.setDescription(description);

        return ContainerMapper.toResponse(containerToUpdate);
    }

    @Transactional
    @Override
    public ContainerResponse updateLocation(Long containerId, Long newStorageId) {
        Long userId = getCurrentUserId();
        Storage newStorage = storageService.getStorageById(newStorageId);
        Container containerToUpdate = containerRepository.findActiveContainerById(containerId, userId)
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        containerToUpdate.setStorage(newStorage);
        return ContainerMapper.toResponse(containerToUpdate);
    }

    @Transactional
    @Override
    public void deleteContainer(Long containerId) {
        Long userId = getCurrentUserId();
        Container containerToDelete = containerRepository.findActiveContainerById(containerId, userId)
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        containerToDelete.setArchived(true);
    }

    private Storage getStorage(Long storageId) {
        return storageService.getStorageById(storageId);
    }

    private Long getCurrentUserId() {
        return currentUserService.getCurrentUser().getId();
    }
}
