package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Project;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;

@Repository
public abstract class ProjectRepository extends Repo<Project> {

    public ProjectRepository(EntityManager em) {
        super(em);
    }
}
