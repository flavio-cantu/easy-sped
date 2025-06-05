package com.cantuaria.bookkeeping;

import com.cantuaria.bookkeeping.model.ConsolidateResponseBookkeeping;
import com.cantuaria.bookkeeping.model.DetailBookkeeping;
import com.cantuaria.bookkeeping.model.ItemResponseBookkeeping;
import com.cantuaria.bookkeeping.model.ResponseBookkeeping;
import com.cantuaria.bookkeeping.model.SaveBookkeeping;
import com.cantuaria.client.Client;
import com.cantuaria.client.ClientRepository;
import com.cantuaria.message.GenericMessageProducer;
import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.sped.BookkeepingRepository;
import com.cantuaria.sped.BookkeepingStatus;
import com.cantuaria.sped.domain.Profile;
import com.cantuaria.sped.domain.Purpose;
import com.cantuaria.sped.domain.layout_version.LayoutVersion;
import com.cantuaria.sped.domain.layout_version.LayoutVersionRepository;
import com.cantuaria.util.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.cantuaria.message.model.ConsolidateMessage.DESTINATION_QUEUE;
import static com.cantuaria.message.model.ConsolidateMessage.RequestConsolidate;


@Service
public class BookkeepingService {

    @Autowired
    private BookkeepingRepository repository;

    @Autowired
    private BookkeepingValidation validation;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LayoutVersionRepository layoutVersionRepository;

    @Autowired
    private GenericMessageProducer genericMessageProducer;


    @Transactional
    public ResponseBookkeeping create(SaveBookkeeping request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));

        LayoutVersion layout = layoutVersionRepository.findById(request.layoutVersionId())
                .orElseThrow(() -> new ObjectNotFoundException("Versão não encontrado"));

        Bookkeeping entity = Bookkeeping.builder()
                .client(client)
                .start(request.start())
                .end(request.end())
                .layoutVersion(layout)
                .purpose(Purpose.findByCode(request.purposeValue()))
                .profile(Profile.findByCode(request.profileValue()))
                .build();

        Bookkeeping savedBookkeeping = repository.save(entity);

        return new ResponseBookkeeping(savedBookkeeping.getId());
    }

    public ResponseBookkeeping update(Long id, SaveBookkeeping bookkeeping) {
        return null;
    }

    public DetailBookkeeping seachById(Long id) {
        return null;
    }

    public List<ItemResponseBookkeeping> findAll() {
        return null;
    }

    @Transactional
    public ConsolidateResponseBookkeeping consolidate(Long id) {
        Bookkeeping bookkeeping = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Escrituração não encontrada"));

        validation.canConsolidate(bookkeeping);

        bookkeeping.setStatus(BookkeepingStatus.PENDING);
        repository.save(bookkeeping);

        //Solicitação para consolidação assíncrona
        genericMessageProducer.sendMessage(DESTINATION_QUEUE,
                new RequestConsolidate(bookkeeping.getId()));

        return new ConsolidateResponseBookkeeping(bookkeeping.getStatus().getCode());
    }

    public ConsolidateResponseBookkeeping reConsolidate(Long id) {
        return null;
    }


    public ConsolidateResponseBookkeeping searchConsolidateById(Long id) {
        Bookkeeping bookkeeping = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Escrituração não encontrada"));

        return new ConsolidateResponseBookkeeping(bookkeeping.getStatus().getCode());
    }
}
