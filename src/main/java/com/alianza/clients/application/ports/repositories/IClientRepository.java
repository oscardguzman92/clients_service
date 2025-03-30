package com.alianza.clients.application.ports.repositories;

import com.alianza.clients.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface IClientRepository {
    List<Client> findAll();
    Optional<Client> findBySharedKey(String sharedKey);
    Client save(Client client);
    Client update(Client client);
}
