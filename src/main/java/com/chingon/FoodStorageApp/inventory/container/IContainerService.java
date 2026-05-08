package com.chingon.FoodStorageApp.inventory.container;

import java.util.List;

public interface IContainerService {
    List<ContainerResponse> getAllContainersForStorage(Long storageId, Long userId);
    List<ContainerResponse> getAllContainers(Long userId);
    ContainerResponse getContainer(Long storageId, Long containerId, Long userId);
    ContainerResponse createContainer(Long storageId, Long userId, String name, String description);
    ContainerResponse updateContainer(Long containerId, Long storageId, Long userId, String name, String description);
    ContainerResponse updateLocation(Long containerId, Long oldStorageId, Long newStorageId, Long userId);
    void deleteContainer(Long containerId, Long storageId, Long userId);

}
