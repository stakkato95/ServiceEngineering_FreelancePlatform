package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Freelancer;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;

@Repository
public abstract class FreelancerRepository extends Repo<Freelancer> {

    public FreelancerRepository(EntityManager em) {
        super(em);
    }
}