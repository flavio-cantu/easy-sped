package com.cantuaria.feature;

import com.cantuaria.client.Client;
import com.cantuaria.client.ClientRepository;
import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.sped.BookkeepingRepository;
import com.cantuaria.sped.domain.Profile;
import com.cantuaria.sped.domain.Purpose;
import com.cantuaria.sped.domain.layout_version.LayoutVersion;
import com.cantuaria.sped.domain.layout_version.LayoutVersionRepository;
import com.cantuaria.util.DateUtil;
import com.cantuaria.util.ObjectNotFoundException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

public class CommomSteps extends GenericSteps {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LayoutVersionRepository layoutVersionRepository;

    @Autowired
    private BookkeepingRepository bookkeepingRepository;

    @Before
    public void reset() {
        bookkeepingRepository.findAll().forEach(entity -> bookkeepingRepository.delete(entity));
        stubFeature.reset();
    }

    @Dado("que exista a seguinte solicitação de escrituração:")
    public void createBookkeeping(DataTable dataTable) {
        dataTable.asMaps().forEach(
                map -> {
                    String idBookkeeping = map.get("ID");
                    String clientBusinessName = map.get("Cliente");
                    String start = map.get("Inicio");
                    String end = map.get("Fim");

                    Client selectedClient = clientRepository.findByBusinessName(clientBusinessName)
                            .stream().filter(client -> client.getBusinessName().equals(clientBusinessName))
                            .findFirst().orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));

                    LayoutVersion layout = layoutVersionRepository.findAll().stream()
                            .findFirst().orElseThrow(() -> new ObjectNotFoundException("Nenhum layout encontrado"));

                    Bookkeeping entity = Bookkeeping.builder()
                            .client(selectedClient)
                            .start(DateUtil.toLocalDate(start))
                            .end(DateUtil.toLocalDate(end))
                            .layoutVersion(layout)
                            .purpose(Purpose.ORIGINAL)
                            .profile(Profile.A)
                            .build();

                    Bookkeeping persistedEntity = bookkeepingRepository.save(entity);

                    Long expectedId = Long.parseLong(idBookkeeping);
                    //Se isso não for verdade, temos lixo na base de dados de teste
                    Assertions.assertThat(persistedEntity.getId()).isEqualTo(expectedId);
                }
        );
    }
}
