package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Project;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class ProjectRepository extends Repo<Project> {
}
