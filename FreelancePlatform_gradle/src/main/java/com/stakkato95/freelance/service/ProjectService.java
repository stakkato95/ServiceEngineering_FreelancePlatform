package com.stakkato95.freelance.service;

import com.stakkato95.freelance.domain.Project;
import com.stakkato95.freelance.domain.transport.NewProject;

import java.util.Optional;

public interface ProjectService {
    Project createProject(NewProject newProject);

    Optional<Project> findProject(Long id);
}
