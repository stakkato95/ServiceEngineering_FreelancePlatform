package com.stakkato95.freelance.service;

import com.stakkato95.freelance.domain.Freelancer;
import com.stakkato95.freelance.domain.Project;
import com.stakkato95.freelance.domain.transport.NewProject;
import com.stakkato95.freelance.repository.FreelancerRepository;
import com.stakkato95.freelance.repository.ProjectRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ProjectServiceImpl implements ProjectService {

    @Inject
    ProjectRepository repo;

    @Inject
    FreelancerRepository freeRepo;

    @Override
    public Project createProject(NewProject newProject) {
        List<Freelancer> freelancers = newProject.getFreelancerIds()
                .stream()
                .map(id -> freeRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        var entity = new Project(null, newProject.getName(), freelancers);
        return repo.merge(entity);
    }

    @Override
    public Optional<Project> findProject(Long id) {
        return repo.findById(id);
    }
}
