package com.cantuaria.consolidate;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.sped.BookkeepingRepository;
import com.cantuaria.sped.BookkeepingStatus;
import com.cantuaria.util.BusinessException;
import com.cantuaria.util.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsolidateService {

    @Autowired
    private BookkeepingRepository repository;
    @Autowired
    private Block0Service block0Service;


    @Transactional
    public void processBookkeeping(Long idBookkeeping) {
        try {
            Bookkeeping bookkeeping = repository.findById(idBookkeeping)
                    .filter(entity -> entity.getStatus() == BookkeepingStatus.PENDING)
                    .orElseThrow(() -> new ObjectNotFoundException("Escrituração não encontrada"));
            bookkeeping.setStatus(BookkeepingStatus.CONSOLIDATED);

            block0Service.parse(bookkeeping).throwIfNotEmpty();
            //block0Service.validateRules(bookkeeping).throwIfNotEmpty();

            repository.save(bookkeeping);
        } catch (BusinessException e) {
            //Gravar validações em algum lugar
            e.getValidations().forEach(validationMessage ->
                    System.out.println(validationMessage.getMessage()));
        }
    }
}
