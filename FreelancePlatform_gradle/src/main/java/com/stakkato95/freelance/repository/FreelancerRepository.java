package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Freelancer;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public abstract class FreelancerRepository implements JpaRepository<Freelancer, Long> {
}
