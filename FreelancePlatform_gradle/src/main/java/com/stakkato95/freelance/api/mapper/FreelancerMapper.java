package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.FreelancerDto;
import com.stakkato95.freelance.domain.Freelancer;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {NestedProjectMapper.class})
public interface FreelancerMapper {

    FreelancerMapper INSTANCE = Mappers.getMapper(FreelancerMapper.class);

    FreelancerDto toDto(Freelancer entity, @Context CycleAvoidingMappingContext context);

    Freelancer toEntity(FreelancerDto dto, @Context CycleAvoidingMappingContext context);
}
