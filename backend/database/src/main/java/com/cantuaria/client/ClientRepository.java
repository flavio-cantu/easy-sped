package com.cantuaria.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dados relacionados ao Cliente
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
