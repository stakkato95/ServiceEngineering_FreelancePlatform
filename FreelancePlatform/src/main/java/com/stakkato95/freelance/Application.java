package com.stakkato95.freelance;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Freelance platform",
                version = "0.1",
                description = "API for accessing freelance platform",
                license = @License(name = "Apache 2.0"),
                contact = @Contact(name = "Kaliaha Artsiom", email = "stakkato95@gmail.com")
        )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
