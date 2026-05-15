package com.chingon.FoodStorageApp.identity.service;

import com.chingon.FoodStorageApp.identity.dto.HouseholdRequest;
import com.chingon.FoodStorageApp.identity.dto.HouseholdResponse;

import java.util.List;
import java.util.UUID;

public interface IHouseholdService {
    HouseholdResponse getHousehold(UUID publicId);
    HouseholdResponse createHousehold(HouseholdRequest householdRequest);
    List<HouseholdResponse> getCurrentUsersHouseholds();
    HouseholdResponse updateHousehold(UUID publicId, HouseholdRequest householdRequest);
    void deleteHousehold(UUID publicId);

}
