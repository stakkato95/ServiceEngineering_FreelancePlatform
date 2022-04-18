package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.ProjectDto;
import com.stakkato95.freelance.domain.Project;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NestedProjectMapper {

    @Mapping(target = "freelancers", ignore = true)
    ProjectDto toDto(Project entity, @Context CycleAvoidingMappingContext context);
}
