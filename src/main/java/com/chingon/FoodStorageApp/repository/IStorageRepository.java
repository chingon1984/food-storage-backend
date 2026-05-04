package com.chingon.FoodStorageApp.repository;

import com.chingon.FoodStorageApp.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IStorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findByUserIdAndArchivedFalse(Long userId);
    Optional<Storage> findByIdAndUserIdAndArchivedFalse(Long id, Long userId);
}
