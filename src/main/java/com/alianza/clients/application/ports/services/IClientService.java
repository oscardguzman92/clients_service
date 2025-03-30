package com.alianza.clients.application.ports.services;

import com.alianza.clients.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
     List<Client> findAllClients() ;

     Optional<Client> findClienteBySharedKey(String sharedKey) ;

    Client createClient(Client client);

    Client updateClient(Client client);
}
