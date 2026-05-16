package com.chingon.FoodStorageApp.identity.service;

import com.chingon.FoodStorageApp.identity.entity.User;
import com.chingon.FoodStorageApp.identity.repository.HouseholdMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseholdAccessService {
    private final ICurrentUserService currentUserService;
    private final HouseholdMemberRepository householdMemberRepository;

    public boolean isCurrentUserMemberOfHousehold(Long householdId) {
        User currentUser = currentUserService.getCurrentUser();

        return householdMemberRepository.existsByUserIdAndHouseholdIdAndArchivedFalse(currentUser.getId(), householdId);
    }

    //    public boolean isCurrentUserOwnerOfHousehold(Long householdId) {
    //        User currentUser = currentUserService.getCurrentUser();
    //
    //        return householdMemberRepository.existsByUserIdAndHouseholdIdAndRoleOwnerAndArchivedFalse(currentUser.getId(), householdId);
    //    }
}
