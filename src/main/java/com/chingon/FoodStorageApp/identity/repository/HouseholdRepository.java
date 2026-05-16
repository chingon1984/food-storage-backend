package com.chingon.FoodStorageApp.identity.repository;

import com.chingon.FoodStorageApp.identity.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
    Optional<Household> findByPublicIdAndArchivedFalse(UUID publicId);
}
