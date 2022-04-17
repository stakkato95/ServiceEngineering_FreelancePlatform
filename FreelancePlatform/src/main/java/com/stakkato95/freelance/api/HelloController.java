package com.stakkato95.freelance.api;

import com.stakkato95.freelance.domain.Customer;
import com.stakkato95.freelance.repository.CustomerRepository;
import com.stakkato95.freelance.repository.CustomerRepositoryImpl;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExecuteOn(TaskExecutors.IO)
@Controller("/hello")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Inject
    CustomerRepositoryImpl repository;

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String hell() {
        return "hello";
    }

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("created");
        var c = new Customer();
        c.setFirstName("first");
        repository.save(c);
    }
}
