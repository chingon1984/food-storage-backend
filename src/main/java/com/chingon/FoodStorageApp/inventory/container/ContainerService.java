package com.chingon.FoodStorageApp.inventory.container;

import com.chingon.FoodStorageApp.inventory.storage.*;
import com.chingon.FoodStorageApp.shared.exception.RessourceNotFoundException;
import com.chingon.FoodStorageApp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContainerService implements IContainerService{
    private final IContainerRepository containerRepository;
    private final IStorageRepository storageRepository;
    private final IStorageService storageService;

    @Override
    public List<ContainerResponse> getAllContainersForStorage(Long storageId, Long userId) {
        return containerRepository.findActiveContainersByStorageId(storageId, userId)
                .stream()
                .map(ContainerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContainerResponse> getAllContainers(Long userId) {
        return containerRepository.findAllActiveContainersByUserId(userId)
                .stream()
                .map(ContainerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ContainerResponse createContainer(Long storageId, Long userId, String name, String description) {
        Storage storageToStoreContainerTo = getStorage(storageId, userId);

        Container container = new Container();
        container.setName(name);
        container.setDescription(description);
        container.setStorage(storageToStoreContainerTo);

        Container savedContainer = containerRepository.save(container);

        return ContainerMapper.toResponse(savedContainer);
    }


    @Transactional
    @Override
    public ContainerResponse updateContainer(Long containerId, Long storageId, Long userId, String name, String description) {
        Container containerToUpdate = containerRepository.findActiveContainerById(containerId, storageId, userId)
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        containerToUpdate.setName(name);
        containerToUpdate.setDescription(description);

        return ContainerMapper.toResponse(containerToUpdate);
    }

    @Transactional
    @Override
    public void deleteContainer(Long containerId, Long storageId, Long userId) {
        Container containerToDelete = containerRepository.findActiveContainerById(containerId, storageId, userId)
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        containerToDelete.setArchived(true);
    }

    private Storage getStorage(Long storageId, Long userId) {
        return storageService.getStorageById(storageId, userId);
    }
}
