package com.chingon.FoodStorageApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class FoodStorageAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodStorageAppApplication.class, args);
	}

}
