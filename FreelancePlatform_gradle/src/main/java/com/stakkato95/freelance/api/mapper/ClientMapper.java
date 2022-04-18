package com.stakkato95.freelance.api.mapper;

import com.stakkato95.freelance.api.dto.ClientDto;
import com.stakkato95.freelance.api.dto.FreelancerDto;
import com.stakkato95.freelance.domain.Client;
import com.stakkato95.freelance.domain.Freelancer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta", uses = NestedMapper.class)
public interface ClientMapper {
    ClientDto toDto(Client entity);

    Client toEntity(ClientDto dto);
}
