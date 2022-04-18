package com.stakkato95.freelance.api.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Introspected
public class NewProjectDto {
    @NotBlank
    String name;

    @Positive
    long clientId;

    @NotEmpty
    List<Long> freelancerIds;
}
