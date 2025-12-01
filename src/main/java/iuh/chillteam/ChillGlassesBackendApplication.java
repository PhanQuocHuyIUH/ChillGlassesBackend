package iuh.chillteam;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "ChillGlasses API",
				version = "1.0.0",
				description = "API Documentation cho hệ thống bán kính mắt ChillGlasses"
		)
)
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT",
		description = "Enter JWT token (without 'Bearer ' prefix)"
)
@SpringBootApplication
public class ChillGlassesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChillGlassesBackendApplication.class, args);
	}

}
