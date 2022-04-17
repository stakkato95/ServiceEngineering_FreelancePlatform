package com.stakkato95.freelance.api.dto;

import com.stakkato95.freelance.domain.Project;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Introspected
public class FreelancerDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String nickName;
    private String email;
    List<Project> projects;
}
