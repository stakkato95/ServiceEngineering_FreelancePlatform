package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.FreelancerDto;
import com.stakkato95.freelance.domain.Freelancer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NestedFreelanceMapper {

    @Mapping(target = "projects", ignore = true)
    FreelancerDto toDto(Freelancer entity);
}
