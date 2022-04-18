package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.NewProjectDto;
import com.stakkato95.freelance.api.dto.ProjectDto;
import com.stakkato95.freelance.domain.Project;
import com.stakkato95.freelance.domain.transport.NewProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {FreelancerMapper.class})
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto toDto(Project entity);

    @Mapping(target = "freelancers", ignore = true)
//    @Mapping(target = "client", ignore = true)
    Project toEntity(ProjectDto dto);

    NewProject toEntity(NewProjectDto dto);
}
