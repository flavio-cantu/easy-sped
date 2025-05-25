package com.cantuaria.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dados relacionados a Empresa
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
