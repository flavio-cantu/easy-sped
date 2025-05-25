package com.cantuaria.fiscal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para objetos relacionado a nota fiscal importada
 */
@Repository
public interface FiscalNoteRepository extends JpaRepository<NotaFiscal, String> {
}
