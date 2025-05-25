package com.cantuaria.sped.domain.gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para os municípios carregados do IBGE para o banco de dados
 */
@Repository
public interface GenderRepository extends JpaRepository<Gender, String> {
}
