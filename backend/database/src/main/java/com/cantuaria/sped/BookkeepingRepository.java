package com.cantuaria.sped;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para a escrituração
 */
@Repository
public interface BookkeepingRepository extends JpaRepository<Bookkeeping, Long> {
}
