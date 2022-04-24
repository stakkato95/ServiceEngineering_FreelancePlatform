package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Client;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;

@Repository
public abstract class ClientRepository extends Repo<Client> {

    public ClientRepository(EntityManager em) {
        super(em);
    }
}
