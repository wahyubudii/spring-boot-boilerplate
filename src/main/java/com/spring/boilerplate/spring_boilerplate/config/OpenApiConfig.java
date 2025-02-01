package com.spring.boilerplate.spring_boilerplate.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Boilerplate Swagger")
                        .version("1.0.0")
                        .description("Created by: @wahyubudii")
                );
    }
}
