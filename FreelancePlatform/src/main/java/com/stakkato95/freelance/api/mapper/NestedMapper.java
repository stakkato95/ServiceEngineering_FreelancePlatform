package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.ClientDto;
import com.stakkato95.freelance.api.dto.FreelancerDto;
import com.stakkato95.freelance.api.dto.ProjectDto;
import com.stakkato95.freelance.domain.Client;
import com.stakkato95.freelance.domain.Freelancer;
import com.stakkato95.freelance.domain.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface NestedMapper {

    @Mapping(target = "projects", ignore = true)
    FreelancerDto toDto(Freelancer entity);

    @Mapping(target = "freelancers", ignore = true)
    ProjectDto toDto(Project entity);

    @Mapping(target = "projects", ignore = true)
    ClientDto toDto(Client entity);
}
