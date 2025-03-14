package com.system.notification.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String securitySchemeName = "bearerAuth";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().packagesToScan("com.system.notification")
                .group("ChatX-services")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI(ServletContext servletContext) {
        Server server = new Server().url(servletContext.getContextPath());
        return new OpenAPI()
                .servers(List.of(server))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
                .info(new Info()
                        .title("Chat API")
                        .description("Chat API Application")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("ChatX")
                        .url(""));
    }
}
