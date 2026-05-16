package com.chingon.FoodStorageApp.shared.annotation;

import io.swagger.v3.oas.annotations.Parameter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Parameter(
        description = "Public ID of the storage",
        example = "355480ae-7c36-4c42-a51f-5172abefe013",
        required = true
)
public @interface StoragePublicIdParameter {
}
