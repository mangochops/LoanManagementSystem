package com.credable.lms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

@OpenAPIDefinition(
    info = @Info(
        title = "Loan Management System API",
        version = "1.0",
        description = "APIs for managing loans"
    )
)
@SpringBootApplication
public class LoanManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanManagementApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        return new WebServiceTemplate();
    }
}
