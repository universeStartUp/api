package com.pe.unieventia.shared.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${unieventia.openapi.dev-url}")
    private String devUrl;

    @Value("${unieventia.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server()
                .url(devUrl)
                .description("Server URL in Development environment");

        Server prodServer = new Server()
                .url(prodUrl)
                .description("Server URL in Production environment");

        Contact contact = new Contact()
                .email("unieventia@gmail.com")
                .name("UniVerse")
                .url("https://file.com/@unieventia");

        License mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("UniEventia Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.")
                .termsOfService("https://www.youtube.com/@unieventia")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}

