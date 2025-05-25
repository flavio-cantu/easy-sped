package com.cantuaria.sped.domain.municipio;

import com.cantuaria.validation.DatabaseSped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para os municípios carregados do IBGE para o banco de dados
 */
@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, String>, DatabaseSped<Municipality, String> {
}
