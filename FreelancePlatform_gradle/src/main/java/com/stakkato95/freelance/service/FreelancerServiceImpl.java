package com.stakkato95.freelance.service;

import com.stakkato95.freelance.domain.Freelancer;
import com.stakkato95.freelance.repository.FreelancerRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class FreelancerServiceImpl implements FreelancerService {

    @Inject
    FreelancerRepository repo;

    @Override
    public Freelancer createFreelancer(Freelancer freelancer) {
        return repo.save(freelancer);
    }

    @Override
    public Optional<Freelancer> findFreelancer(Long id) {
        return repo.findById(id);
    }
}
