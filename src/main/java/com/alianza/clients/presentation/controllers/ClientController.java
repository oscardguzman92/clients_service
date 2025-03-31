package com.alianza.clients.presentation.controllers;

import com.alianza.clients.application.ports.services.IClientService;
import com.alianza.clients.domain.models.Client;
import com.alianza.clients.presentation.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

import static com.alianza.clients.infrastructure.util.ResponseHandler.*;

//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllClients() {
        try {
            List<Client> clients = clientService.findAllClients();
            logger.info("Clients retrieved successfully.");
            ApiResponse<Object> response = new ApiResponse<>(true, "Clients retrieved successfully.", clients);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error retrieving clients", null));
        }
    }

    @GetMapping("/{sharedKey}")
    public ResponseEntity<?> getClient(@PathVariable String sharedKey) {
        try {
            logger.info("Fetching client with sharedKey: {}", sharedKey);
            Optional<Client> client = clientService.findClienteBySharedKey(sharedKey);

            if (client.isPresent()) {
                logger.info("Client found: {}", client.get());
                return handleResponse(ResponseEntity.ok(client));
            } else {
                logger.warn("Client with sharedKey {} not found", sharedKey);
                return handleExceptionNotFound(new RuntimeException(),"Client not found");
            }
        } catch (RestClientException e) {
            return handleException(e, "Failed to retrieve client");
        }
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            System.out.println("CREATE CLIENT");
            logger.info("Creating client: {}", client);
            Client createdClient = clientService.createClient(client);
            logger.info("Client created successfully: {}", createdClient);
            return handleResponse(ResponseEntity.ok(createdClient));
        } catch (Exception e) {
            return handleException(e, "Error creating client");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody Client client) {
        try {
            logger.info("Updating client: {}", client);
            Client createdClient = clientService.updateClient(client);
            logger.info("Client updated successfully: {}", createdClient);
            return handleResponse(ResponseEntity.ok(createdClient));
        } catch (Exception e) {
            return handleException(e, "Error creating client");
        }
    }
}

