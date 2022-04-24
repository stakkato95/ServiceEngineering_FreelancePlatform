package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.FreelancerDto;
import com.stakkato95.freelance.domain.Freelancer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta", uses = NestedMapper.class)
public interface FreelancerMapper {
    FreelancerDto toDto(Freelancer entity);

    Freelancer toEntity(FreelancerDto dto);
}
