package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.NewProjectDto;
import com.stakkato95.freelance.api.dto.ProjectDto;
import com.stakkato95.freelance.domain.Project;
import com.stakkato95.freelance.domain.transport.NewProject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = NestedMapper.class)
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto toDto(Project entity);

    NewProject toEntity(NewProjectDto dto);
}
