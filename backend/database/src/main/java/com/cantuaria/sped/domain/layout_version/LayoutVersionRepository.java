package com.cantuaria.sped.domain.layout_version;

import com.cantuaria.validation.DatabaseSped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para as versões disponíveis.
 * É esperado que aqui sempre tenha a mais atual. No sped, a versão define a validação e
 * a transmissão. Quando vier uma nova versão, vamos precisar atualizar o sistema e a versão atual
 * para que ao enviar para o sped o sistema tenha exeuctado as regras corretas
 */
@Repository
public interface LayoutVersionRepository extends JpaRepository<LayoutVersion, Long>, DatabaseSped<LayoutVersion, String> {
}
