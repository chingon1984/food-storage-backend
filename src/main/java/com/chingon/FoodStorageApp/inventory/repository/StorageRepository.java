package com.chingon.FoodStorageApp.inventory.repository;

import com.chingon.FoodStorageApp.inventory.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    Optional<Storage> findByPublicIdAndArchivedFalse(UUID publicId);

    List<Storage> findByHouseholdIdAndArchivedFalse(Long householdId);

    Optional<Storage> findByIdAndHouseholdIdAndArchivedFalse(Long id, Long householdId);
}
