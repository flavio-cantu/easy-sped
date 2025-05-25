package com.cantuaria.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para as itens, serviços e produtos
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
