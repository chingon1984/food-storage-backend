package com.chingon.FoodStorageApp.identity.mapper;

import com.chingon.FoodStorageApp.identity.entity.Household;
import com.chingon.FoodStorageApp.identity.dto.HouseholdResponse;
import com.chingon.FoodStorageApp.identity.entity.Household_Role;
import org.springframework.stereotype.Component;

@Component
public final class HouseholdMapper {
    private HouseholdMapper() {}

    public static HouseholdResponse toResponse(Household household, Household_Role role) {
        return new HouseholdResponse(
                household.getPublicId(),
                household.getName(),
                household.getDescription(),
                household.getArchived(),
                role
        );
    }

}
