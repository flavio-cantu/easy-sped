package com.cantuaria.nf_layout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Tabela de parâmetro
 * Ela relaciona o cnpj a um 'quebrador de xml'
 * É obrigatório para cadastros que irão enviar xml para o sistema
 */
@Repository
public interface ClientNfLayoutRepository extends JpaRepository<ClientNfLayout, String> {
}
