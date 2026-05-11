package com.chingon.FoodStorageApp.inventory.container.repository;

import com.chingon.FoodStorageApp.inventory.container.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IContainerRepository extends JpaRepository<Container, Long> {
    @Query("""
    SELECT c FROM Container c
    WHERE c.storage.id = :storageId
      AND c.storage.user.id = :userId
      AND c.storage.archived = false
      AND c.archived = false
    """)
    List<Container> findActiveContainersByStorageId(Long storageId, Long userId);

    @Query("""
    SELECT c FROM Container c
      WHERE c.storage.user.id = :userId
      AND c.storage.archived = false
      AND c.archived = false
    """)
    List<Container> findAllActiveContainersByUserId(Long userId);

    @Query("""
            SELECT c FROM Container c
            WHERE c.storage.user.id = :userId
            AND   c.storage.archived = false
            AND   c.id = :containerId
            AND   c.archived = false
            """)
    Optional<Container> findActiveContainerById(Long containerId,  Long userId);
}
