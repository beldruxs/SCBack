package com.belen.phishing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Cubiqfy API", version = "1.0", description = "Cubiqfy API"))

public class PhishingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhishingApplication.class, args);
    }

}
