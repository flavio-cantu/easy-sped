package com.cantuaria.sped.domain.nature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para os códigos de natureza
 */
@Repository
public interface NatureRepository extends JpaRepository<Nature, String> {
}
