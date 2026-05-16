package com.chingon.FoodStorageApp.identity.service;

import com.chingon.FoodStorageApp.identity.entity.Household;
import com.chingon.FoodStorageApp.identity.entity.Household_Role;
import com.chingon.FoodStorageApp.identity.entity.User;
import com.chingon.FoodStorageApp.identity.repository.HouseholdMemberRepository;
import com.chingon.FoodStorageApp.identity.repository.HouseholdRepository;
import com.chingon.FoodStorageApp.shared.exception.RessourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseholdAccessService {
    private final ICurrentUserService currentUserService;
    private final HouseholdMemberRepository householdMemberRepository;
    private final HouseholdRepository householdRepository;

    public boolean hasCurrentUserRole(Long householdId, Household_Role role) {
        User currentUser = currentUserService.getCurrentUser();

        return householdMemberRepository.existsByUserIdAndHouseholdIdAndArchivedFalseAndRole(currentUser.getId(), householdId, role);
    }

    public Household getHouseholdByPublicId(UUID publicId) {
        Household household = householdRepository.findByPublicIdAndArchivedFalse(publicId)
                .orElseThrow(() -> new RessourceNotFoundException("Household", "PublicId", publicId.toString()));

        checkHouseholdPermissions(household);

        return household;
    }

    public void checkHouseholdPermissions(Household household) {
        boolean isMember = isCurrentUserMember(household.getId());

        if (!isMember) {
            throw new RessourceNotFoundException("Household", "PublicId", household.getPublicId().toString());
        }

    }

    public boolean isCurrentUserMember(Long householdId) {
        User currentUser = currentUserService.getCurrentUser();

        return householdMemberRepository.existsByUserIdAndHouseholdIdAndArchivedFalse(currentUser.getId(), householdId);
    }
}
