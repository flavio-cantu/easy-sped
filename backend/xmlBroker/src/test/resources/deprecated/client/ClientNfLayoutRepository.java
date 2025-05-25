package com.cantuaria.nf_layout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientNfLayoutRepository extends JpaRepository<ClientNfLayout, String> {
}
