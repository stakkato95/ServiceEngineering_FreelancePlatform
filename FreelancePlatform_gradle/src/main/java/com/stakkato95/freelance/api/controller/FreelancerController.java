package com.stakkato95.freelance.api.controller;

import com.stakkato95.freelance.api.dto.FreelancerDto;
import com.stakkato95.freelance.api.mapper.FreelancerMapper;
import com.stakkato95.freelance.service.FreelancerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import static com.stakkato95.freelance.ApiConfig.OPEN_API_TAG;
import static com.stakkato95.freelance.ApiConfig.createdLocation;
import static com.stakkato95.freelance.api.controller.FreelancerController.ENDPOINT;

@Slf4j
@Tag(name = OPEN_API_TAG)
@ExecuteOn(TaskExecutors.IO)
@Controller(ENDPOINT)
public class FreelancerController {

    static final String ENDPOINT = "/freelancer";

    @Inject
    FreelancerMapper mapper;

    @Inject
    FreelancerService service;

    @Post
    HttpResponse<FreelancerDto> create(@Body @Valid FreelancerDto dto) {
        var entity = mapper.toEntity(dto);
        var savedEntity = service.createFreelancer(entity);
        var savedDto = mapper.toDto(savedEntity);
        return HttpResponse
                .created(savedDto)
                .headers(headers -> headers.location(createdLocation(ENDPOINT, savedDto.getId())));
    }

    @Get("/{id}")
    HttpResponse<FreelancerDto> get(Long id) {
        return service.findFreelancer(id)
                .map(mapper::toDto)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
