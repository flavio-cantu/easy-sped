package com.cantuaria.sped.domain.additional_information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para as informações complementares
 */
@Repository
public interface AdditionalInformationRepository extends JpaRepository<AdditionalInformation, String> {
}
