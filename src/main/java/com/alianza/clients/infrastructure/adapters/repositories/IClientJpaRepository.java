package com.alianza.clients.infrastructure.adapters.repositories;

import com.alianza.clients.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClientJpaRepository extends JpaRepository<Client, Long> {
    Optional<Client> findBySharedKey(String sharedKey);
}
