package com.stakkato95.freelance.service;

import com.stakkato95.freelance.domain.Client;

import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);

    Optional<Client> findClient(Long id);
}
