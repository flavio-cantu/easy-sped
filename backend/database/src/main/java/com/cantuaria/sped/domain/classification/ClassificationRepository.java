package com.cantuaria.sped.domain.classification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para a classificação das atividades
 */
@Repository
public interface ClassificationRepository extends JpaRepository<Classification, String> {
}
