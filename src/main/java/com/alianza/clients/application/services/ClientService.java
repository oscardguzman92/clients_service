package com.alianza.clients.application.services;

import com.alianza.clients.application.ports.repositories.IClientRepository;
import com.alianza.clients.application.ports.services.IClientService;
import com.alianza.clients.domain.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private final IClientRepository iClientRepository;


    public ClientService(IClientRepository clientRepository) {
        this.iClientRepository = clientRepository;
    }

    @Override
    public List<Client> findAllClients() {
        return iClientRepository.findAll();
    }

    @Override
    public Optional<Client> findClienteBySharedKey(String sharedKey) {
        return iClientRepository.findBySharedKey(sharedKey);
    }

    @Override
    public Client createClient(Client client) {
        return iClientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return iClientRepository.update(client);
    }
}
