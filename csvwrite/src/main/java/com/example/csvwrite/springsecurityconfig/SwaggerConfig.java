package com.example.csvwrite.springsecurityconfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CSV Write User API",
                version = "1.0",
                description = "User Registration and Login APIs"
        )
)
public class SwaggerConfig {
}
