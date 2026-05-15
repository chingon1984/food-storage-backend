package com.chingon.FoodStorageApp.shared.annotation;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@RequestBody(description = "Household Request Dto", required = true)
public @interface HouseholdRequestBody {}
