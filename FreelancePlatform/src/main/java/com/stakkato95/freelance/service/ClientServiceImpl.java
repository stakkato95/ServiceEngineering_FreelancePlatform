package com.stakkato95.freelance.service;

import com.stakkato95.freelance.domain.Client;
import com.stakkato95.freelance.repository.ClientRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
public class ClientServiceImpl implements ClientService {

    @Inject
    ClientRepository repo;

    @Override
    @Transactional
    public Client createClient(Client client) {
        return repo.save(client);
    }

    @Override
    @ReadOnly
    public Optional<Client> findClient(Long id) {
        return repo.findById(id);
    }
}
