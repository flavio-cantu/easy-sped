package com.cantuaria.sped.domain.classification;

import com.cantuaria.validation.DatabaseSped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para a classificação das atividades
 */
@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long>, DatabaseSped<Classification, String> {
}
