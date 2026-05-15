package com.chingon.FoodStorageApp.identity.controller;

import com.chingon.FoodStorageApp.identity.dto.HouseholdRequest;
import com.chingon.FoodStorageApp.identity.dto.HouseholdResponse;
import com.chingon.FoodStorageApp.identity.service.IHouseholdService;
import com.chingon.FoodStorageApp.shared.annotation.HouseholdRequestBody;
import com.chingon.FoodStorageApp.shared.constants.Routes;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(path = Routes.API + Routes.Household.BASE, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class HouseholdController {

    private final IHouseholdService householdService;

    //    @GetMapping()
    //    public ResponseEntity<List<HouseholdResponse>> getAllHouseholds() {
    //        List<HouseholdResponse> householdResponses = householdService.getAllHouseholds();
    //
    //        return ResponseEntity.ok(householdResponses);
    //    }

    @PostMapping()
    public ResponseEntity<HouseholdResponse> createHousehold(
            @RequestBody
            HouseholdRequest householdRequest) {
        System.out.println(householdRequest.name());
        HouseholdResponse householdResponse = householdService.createHousehold(householdRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(householdResponse);
    }

    @PostMapping("/hi")
    public ResponseEntity<HouseholdRequest> test(@RequestBody HouseholdRequest householdRequest) {
        System.out.println(householdRequest);

        return ResponseEntity.ok(householdRequest);
    }


}
