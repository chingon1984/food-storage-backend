package com.chingon.FoodStorageApp.repository;

import com.chingon.FoodStorageApp.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
}
