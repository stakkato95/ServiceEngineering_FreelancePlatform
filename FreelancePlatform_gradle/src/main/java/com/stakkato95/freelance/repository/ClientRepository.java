package com.stakkato95.freelance.repository;

import com.stakkato95.freelance.domain.Client;
import io.micronaut.data.annotation.Repository;

@Repository
public abstract class ClientRepository extends Repo<Client> {
}
