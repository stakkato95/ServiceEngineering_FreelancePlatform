package com.stakkato95.freelance.api.dto;

import com.stakkato95.freelance.domain.Project;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Introspected
public class FreelancerDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String nickName;
    private String email;
    List<Project> projects;
}
