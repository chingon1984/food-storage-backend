package com.chingon.FoodStorageApp.identity.controller;

import com.chingon.FoodStorageApp.identity.dto.HouseholdRequest;
import com.chingon.FoodStorageApp.identity.dto.HouseholdResponse;
import com.chingon.FoodStorageApp.identity.service.IHouseholdService;
import com.chingon.FoodStorageApp.shared.annotation.HouseholdRequestBody;
import com.chingon.FoodStorageApp.shared.constants.Routes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(path = Routes.API + Routes.Household.BASE, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class HouseholdController {

    private final IHouseholdService householdService;

    @PostMapping()
    public ResponseEntity<HouseholdResponse> createHousehold(@Valid @RequestBody @HouseholdRequestBody HouseholdRequest householdRequest) {
        HouseholdResponse householdResponse = householdService.createHousehold(householdRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(householdResponse);
    }

}
