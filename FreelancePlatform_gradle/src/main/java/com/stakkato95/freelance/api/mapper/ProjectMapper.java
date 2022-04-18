package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.NewProjectDto;
import com.stakkato95.freelance.api.dto.ProjectDto;
import com.stakkato95.freelance.domain.Project;
import com.stakkato95.freelance.domain.transport.NewProject;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {NestedFreelanceMapper.class})
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto toDto(Project entity, @Context CycleAvoidingMappingContext context);

    NewProject toEntity(NewProjectDto dto, @Context CycleAvoidingMappingContext context);
}
