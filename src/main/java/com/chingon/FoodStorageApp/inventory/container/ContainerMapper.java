package com.chingon.FoodStorageApp.inventory.container;

import org.springframework.stereotype.Component;

@Component
public final class ContainerMapper {
    private ContainerMapper() {}

    public static ContainerResponse toResponse(Container container) {
        return new ContainerResponse(
                container.getId(),
                container.getName(),
                container.getStorage().getId(),
                container.getDescription(),
                container.getArchived()
        );
    }
}
