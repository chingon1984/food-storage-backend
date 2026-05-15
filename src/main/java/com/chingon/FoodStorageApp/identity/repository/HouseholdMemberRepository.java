package com.chingon.FoodStorageApp.identity.repository;

import com.chingon.FoodStorageApp.identity.entity.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
   List<HouseholdMember> findByUser_IdAndArchivedFalse(Long userId);
   List<HouseholdMember> findByHousehold_IdAndArchivedFalse(Long householdId);
   Optional<HouseholdMember> findByUser_IdAndHousehold_IdAndArchivedFalse(Long userId, Long householdId);
   boolean existsByUser_IdAndHousehold_IdAndArchivedFalse(Long userId, Long householdId);
}
