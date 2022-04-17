package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Customer;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public abstract class CustomerRepositoryImpl implements JpaRepository<Customer, Long> {

//    @PersistenceContext
//    private EntityManager manager;
}
