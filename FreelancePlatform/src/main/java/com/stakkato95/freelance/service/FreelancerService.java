package com.stakkato95.freelance.service;

import com.stakkato95.freelance.domain.Freelancer;

import java.util.Optional;

public interface FreelancerService {
    Freelancer createFreelancer(Freelancer freelancer);

    Optional<Freelancer> findFreelancer(Long id);
}
