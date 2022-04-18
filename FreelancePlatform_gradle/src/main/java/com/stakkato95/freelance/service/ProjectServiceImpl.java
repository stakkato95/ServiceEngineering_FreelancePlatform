package com.stakkato95.freelance.service;

import com.stakkato95.freelance.domain.Project;
import com.stakkato95.freelance.domain.transport.NewProject;
import com.stakkato95.freelance.repository.ClientRepository;
import com.stakkato95.freelance.repository.FreelancerRepository;
import com.stakkato95.freelance.repository.ProjectRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class ProjectServiceImpl implements ProjectService {

    @Inject
    ProjectRepository projectRepo;

    @Inject
    FreelancerRepository freelancerRepo;

    @Inject
    ClientRepository clientRepo;

    @Override
    public Project createProject(NewProject newProject) throws IllegalArgumentException {
        var freelancers = newProject.getFreelancerIds()
                .stream()
                .map(id -> freelancerRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        if (freelancers.isEmpty()) {
            throw new IllegalArgumentException("No valid freelancer ids provided");
        }

        var client = clientRepo.findById(newProject.getClientId());
        if (client.isEmpty()) {
            throw new IllegalArgumentException("Invalid client id");
        }

        var entity = new Project(null, newProject.getName(), client.get(), freelancers);
        return projectRepo.merge(entity);
    }

    @Override
    public Optional<Project> findProject(Long id) {
        return projectRepo.findById(id);
    }
}
