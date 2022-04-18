package com.stakkato95.freelance.repository;

import io.micronaut.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public abstract class Repo<T> implements JpaRepository<T, Long> {

    //    @PersistenceContext
    private final EntityManager em;

    public Repo(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public T merge(T t) {
        return em.merge(t);
    }
}
