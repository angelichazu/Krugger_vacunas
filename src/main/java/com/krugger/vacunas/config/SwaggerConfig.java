package com.krugger.vacunas.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openApi = new OpenAPI();
        openApi.info(
                new Info().title("KRUGER VACUNAS")
                        .description("Reto de vacunas")
                        .version("0.1")
                        .contact(new Contact()
                                .email("kruger.com.ec")
                                .name("kruger")
                                .url("kruger.com.ec"))
                        .termsOfService("")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENCE-2.0"))
        );

        return openApi;
    }

}

