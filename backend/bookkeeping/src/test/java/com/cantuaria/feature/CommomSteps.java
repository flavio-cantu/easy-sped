package com.cantuaria.feature;

import com.cantuaria.client.Client;
import com.cantuaria.client.ClientRepository;
import com.cantuaria.company.Accountant;
import com.cantuaria.company.Company;
import com.cantuaria.company.CompanyRepository;
import com.cantuaria.helper.BookkeepingHelper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CommomSteps extends GenericSteps {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LayoutVersionRepository layoutVersionRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private BookkeepingHelper bookkeepingHelper;


    @Autowired
    private BookkeepingRepository bookkeepingRepository;

    @Before
    public void reset() {
        bookkeepingHelper.clear();
        stubFeature.reset();
    }

    @Dado("que exista a seguinte solicitação de escrituração:")
    @Transactional
    public void createBookkeeping(DataTable dataTable) {
        dataTable.asMaps().forEach(
                map -> {
                    String clientBusinessName = map.get("Cliente");
                    String start = map.get("Inicio");
                    String end = map.get("Fim");

                    Client selectedClient = clientRepository.findByBusinessName(clientBusinessName)
                            .stream().filter(client -> client.getBusinessName().equals(clientBusinessName))
                            .findFirst().orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));

                    LayoutVersion layout = layoutVersionRepository.findAll().stream()
                            .findFirst().orElseThrow(() -> new ObjectNotFoundException("Nenhum layout encontrado"));

                    Company company = companyRepository.findAll().stream()
                            .findFirst().orElseThrow(() -> new ObjectNotFoundException("Nenhuma empresa encontrada"));

                    Accountant accountant = company.getAccountants().stream().findFirst()
                            .orElseThrow(() -> new ObjectNotFoundException("Nenhum contador encontrado"));

                    Bookkeeping entity = Bookkeeping.builder()
                            .client(selectedClient)
                            .company(company)
                            .accountant(accountant)
                            .start(DateUtil.toLocalDate(start))
                            .end(DateUtil.toLocalDate(end))
                            .layoutVersion(layout)
                            .purpose(Purpose.ORIGINAL)
                            .profile(Profile.A)
                            .build();

                    bookkeepingRepository.save(entity);
                }
        );
    }
}
