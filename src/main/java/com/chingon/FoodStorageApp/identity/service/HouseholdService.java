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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseholdService implements IHouseholdService {

    private final HouseholdRepository householdRepository;
    private final HouseholdMemberRepository householdMemberRepository;
    private final CurrentUserService currentUserService;

    /**
     * @param publicId
     * @return
     */
    @Override
    public HouseholdResponse getHousehold(UUID publicId) {
        var requestedHouseholdMember = getCurrentUsersMembership(publicId);

        return HouseholdMapper.toResponse(requestedHouseholdMember.getHousehold(), requestedHouseholdMember.getRole());
    }

    private HouseholdMember getCurrentUsersMembership(UUID publicId) {
        var household = householdRepository.findByPublicIdAndArchivedFalse(publicId)
                .orElseThrow(() -> new RessourceNotFoundException("Household", "publicId", publicId.toString()));

        var user = currentUserService.getCurrentUser();

        return householdMemberRepository.findByUserIdAndHouseholdIdAndArchivedFalse(user.getId(), household.getId())
                .orElseThrow(() -> new RessourceNotFoundException("Household", "publicId", publicId.toString()));
    }

    /**
     * @param householdRequest
     * @return
     */
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

    /**
     * @return
     */
    @Override
    public List<HouseholdResponse> getCurrentUsersHouseholds() {
        User currentUser = currentUserService.getCurrentUser();

        List<HouseholdMember> householdMembers = householdMemberRepository.findByUserIdAndArchivedFalse(currentUser.getId());

        return householdMembers
                .stream()
                .map(member -> HouseholdMapper.toResponse(member.getHousehold(), member.getRole()))
                .toList();
    }

    /**
     * @param publicId
     * @param householdRequest
     * @return
     */
    @Transactional
    @Override
    public HouseholdResponse updateHousehold(UUID publicId, HouseholdRequest householdRequest) {
        var updatableHouseholdMember = getCurrentUsersMembership(publicId);

        checkOwnership(updatableHouseholdMember);

        updatableHouseholdMember.getHousehold().setName(householdRequest.name());
        updatableHouseholdMember.getHousehold().setDescription(householdRequest.description());

        return HouseholdMapper.toResponse(updatableHouseholdMember.getHousehold(), updatableHouseholdMember.getRole());
    }

    private void checkOwnership(HouseholdMember member) {
        if (member.getRole() != Household_Role.OWNER) {
            throw new AccessDeniedException("Only owners can delete households!");
        }
    }

    /**
     * @param publicId
     */
    @Transactional
    @Override
    public void deleteHousehold(UUID publicId) {
        var deletableHouseholdMember = getCurrentUsersMembership(publicId);

        checkOwnership(deletableHouseholdMember);

        deletableHouseholdMember.getHousehold().setArchived(true);

        List<HouseholdMember> deletableHouseholdMembers = householdMemberRepository.findByHouseholdId(deletableHouseholdMember.getHousehold().getId());
        deletableHouseholdMembers.forEach(member -> member.setArchived(true));
    }
}
