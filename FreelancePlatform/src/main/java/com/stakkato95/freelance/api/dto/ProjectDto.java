package com.stakkato95.freelance.api.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Introspected
public class ProjectDto {
    Long id;

    @NotBlank
    String name;

    ClientDto client;

    List<FreelancerDto> freelancers;
}
