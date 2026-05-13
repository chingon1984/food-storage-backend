package com.chingon.FoodStorageApp.inventory.storage.repository;

import com.chingon.FoodStorageApp.inventory.storage.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findByUserIdAndArchivedFalse(Long userId);
    Optional<Storage> findByIdAndUserIdAndArchivedFalse(Long id, Long userId);
}
