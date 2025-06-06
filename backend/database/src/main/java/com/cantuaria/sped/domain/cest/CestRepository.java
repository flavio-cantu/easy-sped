package com.cantuaria.sped.domain.cest;

import com.cantuaria.validation.DatabaseSped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para os Códigos Especificadores de Substituição Tribuitária
 */
@Repository
public interface CestRepository extends JpaRepository<Cest, String>, DatabaseSped<Cest, String> {
}
