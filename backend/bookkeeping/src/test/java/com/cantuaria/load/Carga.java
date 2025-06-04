package com.cantuaria.load;

import com.cantuaria.client.Client;
import com.cantuaria.client.ClientRepository;
import com.cantuaria.sped.domain.ActivityType;
import com.cantuaria.sped.domain.UF;
import com.cantuaria.sped.domain.classification.Classification;
import com.cantuaria.sped.domain.classification.ClassificationRepository;
import com.cantuaria.sped.domain.layout_version.LayoutVersion;
import com.cantuaria.sped.domain.layout_version.LayoutVersionRepository;
import com.cantuaria.sped.domain.municipio.Municipality;
import com.cantuaria.sped.domain.municipio.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class Carga implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MunicipalityRepository municipalityRepository;
    @Autowired
    private ClassificationRepository classificationRepository;
    @Autowired
    private LayoutVersionRepository layoutVersionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (clientRepository.findAll().isEmpty()) {
            createVersionLayout();
            Municipality brasilia = createMunicipality();
            Classification classification = createClassification();
            createGenericClient(brasilia, classification);
        }
    }

    private void createVersionLayout() {
        layoutVersionRepository.save(LayoutVersion.builder()
                        .spedCode("19")
                        .version("19.9")
                        .start(LocalDate.of(2025,1,1))
                .build());
    }

    private Classification createClassification() {
        return classificationRepository.save(Classification.builder()
                .spedCode("01")
                .description("Posto de gasolina")
                .build());
    }

    private Municipality createMunicipality() {
        return municipalityRepository.save(Municipality.builder()
                .spedCode("1")
                .name("Brasília")
                .build());
    }

    private void createGenericClient(Municipality brasilia, Classification classification) {
        clientRepository.save(Client.builder()
                .businessName("Posto Shell")
                .cnpj("03403210000119")
                .fantasyName("Posto do Teo")
                .responsibleName("Arnaldo do Santos")
                .responsibleCpf("97832522089")
                .uf(UF.DF)
                .stateRegistration("07275245001-57")
                .municipality(brasilia)
                .municipalRegistration("07443933001-09")
                .activityType(ActivityType.OUTRAS)
                .cep("72335501")
                .address("Quadra QS 629 Conjunto A")
                .neighborhood("Samambaia Norte")
                .phone("06198543972")
                .email("arnaldo@teste.simulado.com")
                .classification(classification)
                .build());
    }


}
