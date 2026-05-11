package com.chingon.FoodStorageApp.inventory.container.service;

import com.chingon.FoodStorageApp.inventory.container.dto.ContainerResponse;

import java.util.List;

public interface IContainerService {
    List<ContainerResponse> getAllContainersForStorage(Long storageId);
    List<ContainerResponse> getAllContainers();
    ContainerResponse getContainer(Long containerId);
    ContainerResponse createContainer(Long storageId, String name, String description);
    ContainerResponse updateContainer(Long containerId, String name, String description);
    ContainerResponse updateLocation(Long containerId, Long newStorageId);
    void deleteContainer(Long containerId);

}
