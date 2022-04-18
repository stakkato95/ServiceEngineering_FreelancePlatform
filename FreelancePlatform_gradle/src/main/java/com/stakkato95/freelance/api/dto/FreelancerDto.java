package com.stakkato95.freelance.api.dto;

import com.stakkato95.freelance.domain.Project;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Introspected
public class FreelancerDto {
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

    @NotBlank
    private String nickName;

    @Email
    private String email;

    List<Project> projects;
}
