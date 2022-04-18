package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.FreelancerDto;
import com.stakkato95.freelance.domain.Freelancer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FreelancerMapper {

    FreelancerMapper INSTANCE = Mappers.getMapper(FreelancerMapper.class);

    @Mapping(target = "projects", ignore = true)
    FreelancerDto toDto(Freelancer entity);

    @Mapping(target = "projects", ignore = true)
    Freelancer toEntity(FreelancerDto dto);
}
