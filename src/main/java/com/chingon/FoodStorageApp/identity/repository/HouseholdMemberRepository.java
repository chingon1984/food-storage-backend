package com.chingon.FoodStorageApp.identity.repository;

import com.chingon.FoodStorageApp.identity.entity.HouseholdMember;
import com.chingon.FoodStorageApp.identity.entity.Household_Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
    List<HouseholdMember> findByUserIdAndArchivedFalse(Long userId);

    List<HouseholdMember> findByHouseholdId(Long householdId);

    Boolean existsByUserIdAndHouseholdIdAndArchivedFalse(Long userId, Long householdId);

    Boolean existsByUserIdAndHouseholdIdAndArchivedFalseAndRole(Long userId, Long householdId, Household_Role role);

    Optional<HouseholdMember> findByUserIdAndHouseholdIdAndArchivedFalse(Long userId, Long householdId);
}
