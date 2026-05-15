package com.chingon.FoodStorageApp.identity.repository;

import com.chingon.FoodStorageApp.identity.entity.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
    List<HouseholdMember> findByUserIdAndArchivedFalse(Long userId);

    List<HouseholdMember> findByHouseholdId(Long householdId);

    Optional<HouseholdMember> findByUserIdAndHouseholdIdAndArchivedFalse(Long userId, Long householdId);
}
