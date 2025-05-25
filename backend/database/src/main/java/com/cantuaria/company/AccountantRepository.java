package com.cantuaria.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dados relacionados ao Contador
 */
@Repository
public interface AccountantRepository extends JpaRepository<Accountant, Long> {
}
