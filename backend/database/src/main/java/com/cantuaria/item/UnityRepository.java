package com.cantuaria.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para as unidades de metidas utilizadas na EFD
 */
@Repository
public interface UnityRepository extends JpaRepository<Unity, String> {
}
