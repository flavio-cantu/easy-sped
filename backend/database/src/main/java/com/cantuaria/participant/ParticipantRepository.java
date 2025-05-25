package com.cantuaria.participant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para os participantes (Clientes, Fornecedores e Outros)
 */
@Repository
public interface ParticipantRepository extends JpaRepository<Participant, String> {
}
