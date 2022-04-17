package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Customer;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class CustomerRepositoryImpl implements CustomerRepository {

    //    @PersistenceContext
    private final EntityManager manager;

    public CustomerRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        manager.persist(customer);
    }
}
