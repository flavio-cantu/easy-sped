package com.cantuaria.load;

import com.cantuaria.client.Client;
import com.cantuaria.client.ClientRepository;
import com.cantuaria.company.Accountant;
import com.cantuaria.company.Company;
import com.cantuaria.item.Item;
import com.cantuaria.item.ItemHistory;
import com.cantuaria.item.ItemType;
import com.cantuaria.item.Unity;
import com.cantuaria.participant.Participant;
import com.cantuaria.sped.domain.ActivityType;
import com.cantuaria.sped.domain.UF;
import com.cantuaria.sped.domain.anp.Anp;
import com.cantuaria.sped.domain.cest.Cest;
import com.cantuaria.sped.domain.classification.Classification;
import com.cantuaria.sped.domain.country.Country;
import com.cantuaria.sped.domain.gender.Gender;
import com.cantuaria.sped.domain.layout_version.LayoutVersion;
import com.cantuaria.sped.domain.municipio.Municipality;
import jakarta.persistence.EntityManager;
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
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (clientRepository.findAll().isEmpty()) {
            Country brasil = createCountry();
            createVersionLayout();
            Municipality brasilia = createMunicipality();
            Classification classification = createClassification();
            Client client = createGenericClient(brasilia, classification);

            Company company = createCompany(brasilia);
            createAccountant(company);

            Participant providerA = createParticipant("11853438000139", "Fornecedor A", brasil, brasilia);
            addParticipantToClient(client, providerA);

            Participant providerB = createParticipant("57607648020", "Fornecedor B", brasil, brasilia);
            addParticipantToClient(client, providerB);

            Unity unity = createUnity(client);
            Gender gender = createGender();
            Cest cest = createCest();
            Anp anp = createAnp();

            Item gasolinaComum = createItem(client, unity, gender, cest, anp);
            addSomeHistory(gasolinaComum);
        }
    }

    private void addSomeHistory(Item item) {
        entityManager.persist(ItemHistory.builder()
                .startDate(item.getLastDescriptionModification().toLocalDate())
                .endDate(LocalDate.of(2025, 6, 10))
                .previousDescription("G. COMUM")
                .item(item)
                .build());

        entityManager.persist(ItemHistory.builder()
                .startDate(item.getLastCodeModification().toLocalDate())
                .endDate(LocalDate.of(2025, 6, 20))
                .previousCode("COMB_GC01")
                .item(item)
                .build());
    }

    private Anp createAnp() {
        return entityManager.merge(Anp.builder()
                .spedCode("010101")
                .group("COMBUSTIVEL")
                .name("GASOLINA COMUM")
                .build());
    }

    private Cest createCest() {
        return entityManager.merge(Cest.builder()
                .spedCode("X19")
                .name("GENERICO CEST")
                .build());
    }

    private Gender createGender() {
        return entityManager.merge(Gender.builder()
                .spedCode("01")
                .name("POSTO DE COMBUSTIVEL")
                .build());
    }

    private Item createItem(Client client, Unity unity, Gender gender, Cest cest, Anp anp) {
        Item item = entityManager.merge(Item.builder()
                .itemCode("COMB01")
                .lastCodeModification(LocalDate.of(2020, 5, 1).atStartOfDay())
                .description("GASOLINA COMUM")
                .lastDescriptionModification(LocalDate.of(2020, 5, 1).atStartOfDay())
                .unity(unity)
                .conversionUnity(unity)
                .itemType(ItemType.SERVICOS)
                .mercosulCode("BR61594")
                .gender(gender)
                .serviceCode("59-65")
                .conversionFactor(1.0)
                .aliquotICMS(0.15)
                .cest(cest)
                .anp(anp)
                .build());

        client.getItens().add(item);
        entityManager.persist(client);
        return item;
    }

    private Unity createUnity(Client client) {
        Unity unity = entityManager.merge(Unity.builder()
                .spedCode("GAS01")
                .name("GASOLINA COMUM")
                .build());

        client.getUnities().add(unity);
        entityManager.persist(client);
        return unity;
    }

    private void addParticipantToClient(Client client, Participant participant) {
        client.getParticipants().add(participant);
        entityManager.persist(client);
    }

    private Country createCountry() {
        Country country = new Country();
        country.setSpedCode("1058");
        country.setName("BRASIL");
        return entityManager.merge(country);
    }

    private Participant createParticipant(String cnpjCpf, String name, Country country, Municipality municipality) {
        Participant participant = Participant.builder()
                .code(cnpjCpf)
                .name(name)
                .country(country)
                .stateRegistration("57763000188")
                .municipality(municipality)
                .suframa("2030797")
                .address("Quadra QNO 7 Conjunto B")
                .number("7")
                .neighborhood("Ceilândia Norte")
                .build();

        if (cnpjCpf.length() == 11) {
            participant.setCpf(cnpjCpf);
        } else {
            participant.setCnpj(cnpjCpf);
        }

        return entityManager.merge(participant);
    }

    private Company createCompany(Municipality brasilia) {
        return entityManager.merge(Company.builder()
                .cnpj("93953122000183")
                .cep("73355409")
                .address("Quadra Quadra 14 Conjunto 9")
                .neighborhood("Setor Residencial Leste")
                .municipality(brasilia)
                .build());
    }

    private void createAccountant(Company company) {
        entityManager.persist(Accountant.builder()
                .name("Aroldo")
                .cpf("67444945039")
                .crc("20865900086")
                .phone("06199738472")
                .email("aroldo@contador.com")
                .company(company)
                .build());
    }

    private void createVersionLayout() {
        entityManager.persist(LayoutVersion.builder()
                .spedCode("19")
                .version("19.9")
                .start(LocalDate.of(2025, 1, 1))
                .build());
    }

    private Classification createClassification() {
        return entityManager.merge(Classification.builder()
                .spedCode("01")
                .description("Posto de gasolina")
                .build());
    }

    private Municipality createMunicipality() {
        return entityManager.merge(Municipality.builder()
                .spedCode("1")
                .name("Brasília")
                .build());
    }

    private Client createGenericClient(Municipality brasilia, Classification classification) {
        return entityManager.merge(Client.builder()
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
