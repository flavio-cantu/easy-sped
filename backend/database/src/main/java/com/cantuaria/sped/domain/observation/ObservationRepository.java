package com.cantuaria.sped.domain.observation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para as observações do lançamento fiscal
 */
@Repository
public interface ObservationRepository extends JpaRepository<Observation, String> {
}
