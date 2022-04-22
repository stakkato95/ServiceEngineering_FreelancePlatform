package com.stakkato95.freelance.api.controller;

import com.stakkato95.freelance.api.dto.NewProjectDto;
import com.stakkato95.freelance.api.dto.ProjectDto;
import com.stakkato95.freelance.api.mapper.ProjectMapper;
import com.stakkato95.freelance.service.ProjectService;
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
import static com.stakkato95.freelance.api.controller.ProjectController.ENDPOINT;

@Slf4j
@Tag(name = OPEN_API_TAG)
@ExecuteOn(TaskExecutors.IO)
@Controller(ENDPOINT)
public class ProjectController {

    static final String ENDPOINT = "/project";

    @Inject
    ProjectMapper mapper;

    @Inject
    ProjectService service;

    @Post
    HttpResponse<ProjectDto> create(@Body @Valid NewProjectDto dto) {
        var newProject = mapper.toEntity(dto);
        var createdEntity = service.createProject(newProject);
        var savedDto = mapper.toDto(createdEntity);
        return HttpResponse
                .created(savedDto)
                .headers(headers -> headers.location(createdLocation(ENDPOINT, savedDto.getId())));
    }

    @Get("/{id}")
    HttpResponse<ProjectDto> get(Long id) {
        return service.findProject(id)
                .map(mapper::toDto)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
