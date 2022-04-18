package com.stakkato95.freelance.api.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.List;

@Data
@Introspected
public class ProjectDto {
    Long id;
    String name;
    //    Client client;
    List<FreelancerDto> freelancers;
}
