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
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import static com.stakkato95.freelance.ApiConfig.createdLocation;
import static com.stakkato95.freelance.api.controller.ProjectController.ENDPOINT;

@Slf4j
@ExecuteOn(TaskExecutors.IO)
@Controller(ENDPOINT)
public class ProjectController {

    static final String ENDPOINT = "/project";

    private static final ProjectMapper MAPPER = ProjectMapper.INSTANCE;

    @Inject
    ProjectService service;

    @Post
    HttpResponse<ProjectDto> create(@Body @Valid NewProjectDto dto) {
        var newProject = MAPPER.toEntity(dto);
        var createdEntity = service.createProject(newProject);
        var savedDto = MAPPER.toDto(createdEntity);
        return HttpResponse
                .created(savedDto)
                .headers(headers -> headers.location(createdLocation(ENDPOINT, savedDto.getId())));
    }

    @Get("/{id}")
    HttpResponse<ProjectDto> get(Long id) {
        return service.findProject(id)
                .map(MAPPER::toDto)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
