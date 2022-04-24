package com.stakkato95.freelance.service;

import com.stakkato95.freelance.domain.Freelancer;
import com.stakkato95.freelance.repository.FreelancerRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
public class FreelancerServiceImpl implements FreelancerService {

    @Inject
    FreelancerRepository repo;

    @Override
    @Transactional
    public Freelancer createFreelancer(Freelancer freelancer) {
        return repo.save(freelancer);
    }

    @Override
    @ReadOnly
    public Optional<Freelancer> findFreelancer(Long id) {
        return repo.findById(id);
    }
}
