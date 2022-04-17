package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Project;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class ProjectRepository implements JpaRepository<Project, Long> {

    @PersistenceContext()
    EntityManager em;

    @Transactional
    public Project merge(Project project) {
        return em.merge(project);
    }
}
