package com.cantuaria.sped.domain.anp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para os Códigos de produto na ANP
 */
@Repository
public interface AnpRepository extends JpaRepository<Anp, String> {
}
