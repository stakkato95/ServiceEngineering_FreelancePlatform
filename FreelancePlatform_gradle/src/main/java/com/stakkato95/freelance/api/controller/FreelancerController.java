package com.stakkato95.freelance.api.controller;

import com.stakkato95.freelance.api.dto.FreelancerDto;
import com.stakkato95.freelance.api.mapper.FreelancerMapper;
import com.stakkato95.freelance.repository.FreelancerRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
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

    private static final FreelancerMapper MAPPER = FreelancerMapper.INSTANCE;

    @Inject
    FreelancerRepository repo;

    @Post
    HttpResponse<FreelancerDto> create(@Body @Valid FreelancerDto dto) {
        var entity = MAPPER.toEntity(dto);
        var savedEntity = repo.save(entity);
        var savedDto = MAPPER.toDto(savedEntity);
        return HttpResponse
                .created(savedDto)
                .headers(headers -> headers.location(createdLocation(ENDPOINT, savedDto.getId())));
    }

    @Get("/{id}")
    HttpResponse<FreelancerDto> get(Long id) {
        return repo.findById(id)
                .map(MAPPER::toDto)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
