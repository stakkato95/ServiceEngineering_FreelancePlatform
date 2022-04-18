package com.stakkato95.freelance.service;

import com.stakkato95.freelance.domain.Client;
import com.stakkato95.freelance.repository.ClientRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class ClientServiceImpl implements ClientService {

    @Inject
    ClientRepository repo;

    @Override
    public Client createClient(Client client) {
        return repo.save(client);
    }

    @Override
    public Optional<Client> findClient(Long id) {
        return repo.findById(id);
    }
}
