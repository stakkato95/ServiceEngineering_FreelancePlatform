package com.stakkato95.freelance.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Introspected
public class FreelancerDto {
    Long id;

    @NotBlank
    String firstName;

    @NotBlank
    String secondName;

    @NotBlank
    String nickName;

    @Email
    String email;

    List<ProjectDto> projects;
}
