package com.chingon.FoodStorageApp;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "FoodStorage REST Api Documentation",
				description = "Documentation for the FoodStorage REST Api",
				version = "v1",
				contact = @Contact(
						name = "Patrick v. Hasselbach",
						email = "patrick.hasselbach@protonmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Documentation for the FoodStorage REST Api"
		)
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class FoodStorageAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodStorageAppApplication.class, args);
	}

}
