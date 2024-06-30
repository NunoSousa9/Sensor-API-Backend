package com.example.sensorapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.TreeMap;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Sensor API")
                    .version("1.0")
                    .description("API for Sensor Data Management"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("sensor-api")
                .pathsToMatch("/sensors/**")
                .addOpenApiCustomizer(orderOperations())
                .build();
    }

    private OpenApiCustomizer orderOperations() {
        return openApi -> {
            Paths paths = openApi.getPaths();
            if (paths != null) {
                Map<String, PathItem> sortedPaths = new TreeMap<>(new CustomOperationComparator());
                sortedPaths.putAll(paths);
                Paths newPaths = new Paths();
                sortedPaths.forEach(newPaths::addPathItem);
                openApi.setPaths(newPaths);
            }
        };
    }
}
