package com.limite_certo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SwaggerConfigTest {

    @Test
    public void testCustomOpenAPI() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        OpenAPI openAPI = swaggerConfig.customOpenAPI();

        assertThat(openAPI).isNotNull();

        assertThat(openAPI.getSecurity()).hasSize(1);
        SecurityRequirement securityRequirement = openAPI.getSecurity().get(0);
        assertThat(securityRequirement.get("bearerAuth")).isNotNull();

        assertThat(openAPI.getComponents()).isNotNull();
        assertThat(openAPI.getComponents().getSecuritySchemes()).containsKey("bearerAuth");
        assertThat(openAPI.getComponents().getSecuritySchemes().get("bearerAuth").getScheme()).isEqualTo("bearer");
        assertThat(openAPI.getComponents().getSecuritySchemes().get("bearerAuth").getBearerFormat()).isEqualTo("JWT");
    }
}
