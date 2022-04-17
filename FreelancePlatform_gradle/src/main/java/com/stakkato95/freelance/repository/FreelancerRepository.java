package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Freelancer;
import io.micronaut.data.annotation.Repository;

@Repository
public abstract class FreelancerRepository extends Repo<Freelancer> {
}