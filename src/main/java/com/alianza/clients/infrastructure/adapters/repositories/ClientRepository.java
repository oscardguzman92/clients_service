package com.alianza.clients.infrastructure.adapters.repositories;

import com.alianza.clients.application.ports.repositories.IClientRepository;
import com.alianza.clients.domain.models.Client;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository implements IClientRepository {

    private final IClientJpaRepository iClientJpaRepository;

    public ClientRepository(IClientJpaRepository iClientJpaRepository) {
        this.iClientJpaRepository = iClientJpaRepository;
    }

    @Override
    public List<Client> findAll() {
        return iClientJpaRepository.findAll();
    }

    @Override
    public Optional<Client> findBySharedKey(String sharedKey) {
        return iClientJpaRepository.findBySharedKey(sharedKey);
    }

    @Override
    public Client save(Client client) {
        return iClientJpaRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        System.out.println("CLIENTE");
        System.out.println(client);
        if (client.getId() == null || !iClientJpaRepository.existsById(client.getId())) {
            throw new EntityNotFoundException("Client with ID " + client.getId() + " not found.");
        }
        return iClientJpaRepository.save(client);
    }
}
