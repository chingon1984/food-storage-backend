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
    public List<ContainerResponse> getAllContainersForStorage(Long storageId) {
        User currentUser = getCurrentUser();
        return containerRepository.findActiveContainersByStorageId(storageId, currentUser.getId())
                .stream()
                .map(ContainerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContainerResponse> getAllContainers() {
        User currentUser = getCurrentUser();
        return containerRepository.findAllActiveContainersByUserId(currentUser.getId())
                .stream()
                .map(ContainerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ContainerResponse getContainer(Long containerId) {
        User currentUser = getCurrentUser();
        Container requestedContainer = containerRepository.findActiveContainerById(containerId,  currentUser.getId())
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        return ContainerMapper.toResponse(requestedContainer);
    }


    @Override
    public ContainerResponse createContainer(Long storageId, String name, String description) {
        User currentUser = getCurrentUser();
        Storage storageToStoreContainerTo = getStorage(storageId, currentUser.getId());

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
        User currentUser = getCurrentUser();
        Container containerToUpdate = containerRepository.findActiveContainerById(containerId, currentUser.getId())
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        containerToUpdate.setName(name);
        containerToUpdate.setDescription(description);

        return ContainerMapper.toResponse(containerToUpdate);
    }

    @Transactional
    @Override
    public ContainerResponse updateLocation(Long containerId, Long newStorageId) {
        User currentUser = getCurrentUser();
        Storage newStorage = storageService.getStorageById(newStorageId, currentUser.getId());
        Container containerToUpdate = containerRepository.findActiveContainerById(containerId, currentUser.getId())
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        containerToUpdate.setStorage(newStorage);
        return ContainerMapper.toResponse(containerToUpdate);
    }

    @Transactional
    @Override
    public void deleteContainer(Long containerId) {
        User currentUser = getCurrentUser();
        Container containerToDelete = containerRepository.findActiveContainerById(containerId, currentUser.getId())
                .orElseThrow(() -> new RessourceNotFoundException("Container", "ID", containerId.toString()));

        containerToDelete.setArchived(true);
    }

    private Storage getStorage(Long storageId, Long userId) {
        return storageService.getStorageById(storageId, userId);
    }

    private User getCurrentUser() {
       User user = new User();
       user.setId(1L);

       return user;
    }
}
