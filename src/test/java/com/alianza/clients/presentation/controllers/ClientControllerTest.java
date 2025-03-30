package com.alianza.clients.presentation.controllers;

import com.alianza.clients.application.ports.services.IClientService;
import com.alianza.clients.domain.models.Client;
import com.alianza.clients.presentation.dto.ApiResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class ClientControllerTest {

    @Mock
    private IClientService iClientService;

    @InjectMocks
    private ClientController clientController;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client(1L, "jdoe", "John Doe", "jdoe@example.com", "3219876543", "20/05/2022");
    }

    @Test
    public void testCreateClient() {
        when(iClientService.createClient(client)).thenReturn(client);

        ResponseEntity<?> response = clientController.createClient(client);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(ApiResponse.class, response.getBody());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertTrue(apiResponse.isSuccess());
        assertEquals("Client created successfully", apiResponse.getMessage());
        assertEquals(client, apiResponse.getData());
    }

    @Test
    public void testGetAllClients() {
        List<Client> clients = Arrays.asList(client, new Client(2L, "jsmith", "Jane Smith", "jsmith@example.com", "3219876543", "21/05/2022"));
        when(iClientService.findAllClients()).thenReturn(clients);

        ResponseEntity<?> response = clientController.getAllClients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertInstanceOf(ApiResponse.class, response.getBody());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertEquals("Clients retrieved successfully", apiResponse.getMessage());
        assertEquals(clients, apiResponse.getData());
    }

    @Test
    public void testGetClientFound() {
        when(iClientService.findClienteBySharedKey("jdoe")).thenReturn(Optional.of(client));

        ResponseEntity<?> response = clientController.getClient("jdoe");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(ApiResponse.class, response.getBody());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertTrue(apiResponse.isSuccess());
        assertEquals("Success", apiResponse.getMessage());
        assertEquals(client, apiResponse.getData());
    }

    @Test
    public void testGetClientNotFound() {
        when(iClientService.findClienteBySharedKey("unknown")).thenReturn(Optional.empty());

        ResponseEntity<?> response = clientController.getClient("unknown");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(ApiResponse.class, response.getBody());
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();
        assertFalse(apiResponse.isSuccess());
        assertEquals("Client not found", apiResponse.getMessage());
        assertNull(apiResponse.getData());
    }

}
