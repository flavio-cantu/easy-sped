package com.cantuaria.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dados relacionados ao Cliente
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByBusinessName(String clientBusinessName);
}
