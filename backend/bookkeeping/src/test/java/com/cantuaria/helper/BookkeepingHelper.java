package com.cantuaria.helper;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.sped.BookkeepingRepository;
import com.cantuaria.sped.block_0.Block0;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookkeepingHelper {

    @Autowired
    private BookkeepingRepository bookkeepingRepository;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void clear() {
        bookkeepingRepository.findAll().forEach(entity -> {
            removeBlock0(entity);
            bookkeepingRepository.delete(entity);
        });
    }

    private void removeBlock0(Bookkeeping entity) {
        Block0 block0 = entity.getBlock0();
        if (block0 == null) {
            return;
        }
        remove(block0.getRecord0000());
        remove(block0.getRecord0001());
        remove(block0.getRecord0002());
        remove(block0.getRecord0005());
        remove(block0.getRecord0100());
        //TODO ficar de olho se o cascade vai funcionar nessa lista
        //remove(block0.getRecords0150());
        //remove(block0.getRecords0190());
        //remove(block0.getRecords0200());
        //remove(block0.getRecords0400());
        remove(block0);
    }

    private void remove(Object o) {
        if (o == null) {
            return;
        }
        entityManager.remove(o);
    }
}
