package com.stakkato95.freelance.api.controller;

import com.stakkato95.freelance.domain.Freelancer;
import com.stakkato95.freelance.repository.FreelancerRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import static com.stakkato95.freelance.ApiConfig.createdLocation;
import static com.stakkato95.freelance.api.controller.FreelancerController.ENDPOINT;

@Slf4j
@ExecuteOn(TaskExecutors.IO)
@Controller(ENDPOINT)
public class FreelancerController {

    static final String ENDPOINT = "/freelancer";

    @Inject
    FreelancerRepository repo;

    @Post
    HttpResponse<Freelancer> create(@Body @Valid Freelancer freelancer) {
        var savedFreelancer = repo.save(freelancer);
        return HttpResponse
                .created(savedFreelancer)
                .headers(headers -> headers.location(createdLocation(ENDPOINT, savedFreelancer.getId())));
    }
}
