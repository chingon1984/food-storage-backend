package com.chingon.FoodStorageApp.identity.service;

import com.chingon.FoodStorageApp.identity.dto.HouseholdRequest;
import com.chingon.FoodStorageApp.identity.dto.HouseholdResponse;
import com.chingon.FoodStorageApp.identity.entity.Household;
import com.chingon.FoodStorageApp.identity.entity.HouseholdMember;
import com.chingon.FoodStorageApp.identity.entity.Household_Role;
import com.chingon.FoodStorageApp.identity.entity.User;
import com.chingon.FoodStorageApp.identity.mapper.HouseholdMapper;
import com.chingon.FoodStorageApp.identity.repository.HouseholdMemberRepository;
import com.chingon.FoodStorageApp.identity.repository.HouseholdRepository;
import com.chingon.FoodStorageApp.shared.exception.RessourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseholdService implements IHouseholdService{

    private final HouseholdRepository householdRepository;
    private final HouseholdMemberRepository householdMemberRepository;
    private final CurrentUserService currentUserService;

    @Override
    public HouseholdResponse getHousehold(UUID publicId) {
        return null;
    }

    @Transactional
    @Override
    public HouseholdResponse createHousehold(HouseholdRequest householdRequest) {
        User currentUser = currentUserService.getCurrentUser();

        Household household = new Household();
        household.setPublicId(UUID.randomUUID());
        household.setName(householdRequest.name());
        household.setDescription(householdRequest.description());
        Household savedHousehold = householdRepository.save(household);

        HouseholdMember householdMember = new HouseholdMember();
        householdMember.setHousehold(household);
        householdMember.setUser(currentUser);
        householdMember.setRole(Household_Role.OWNER);
        HouseholdMember savedHouseholdMember = householdMemberRepository.save(householdMember);

        return HouseholdMapper.toResponse(savedHousehold, savedHouseholdMember.getRole());
    }

    @Override
    public List<HouseholdResponse> getCurrentUsersHouseholds() {
        return List.of();
    }

    @Override
    public HouseholdResponse updateHousehold(UUID publicId, HouseholdRequest householdRequest) {
        return null;
    }

    @Override
    public void deleteHousehold(UUID publicId) {

    }
}
