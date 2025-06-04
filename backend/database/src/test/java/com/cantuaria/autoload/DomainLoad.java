package com.cantuaria.autoload;

import com.cantuaria.sped.domain.anp.Anp;
import com.cantuaria.sped.domain.anp.AnpRepository;
import com.cantuaria.sped.domain.cest.Cest;
import com.cantuaria.sped.domain.cest.CestRepository;
import com.cantuaria.sped.domain.classification.Classification;
import com.cantuaria.sped.domain.classification.ClassificationRepository;
import com.cantuaria.sped.domain.country.Country;
import com.cantuaria.sped.domain.country.CountryRepository;
import com.cantuaria.sped.domain.gender.Gender;
import com.cantuaria.sped.domain.gender.GenderRepository;
import com.cantuaria.sped.domain.layout_version.LayoutVersion;
import com.cantuaria.sped.domain.layout_version.LayoutVersionRepository;
import com.cantuaria.sped.domain.municipio.Municipality;
import com.cantuaria.sped.domain.municipio.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Order(1)
@Component
public class DomainLoad implements CommandLineRunner {

    @Autowired
    private LayoutVersionRepository layoutVersionRepository;
    @Autowired
    private MunicipalityRepository municipalityRepository;
    @Autowired
    private ClassificationRepository classificationRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private CestRepository cestRepository;
    @Autowired
    private AnpRepository anpRepository;

    @Override
    public void run(String... args) throws Exception {
        if (layoutVersionRepository.findAll().isEmpty()) {
            addLayoutVersion();
            addMunicipality();
            addClassification();
            addCountry();
            addItemGender();
            addCest();
            addAnp();
        }
    }

    private void addAnp() {
        Anp anp = new Anp();
        anp.setSpedCode("320102001");
        anp.setName("Gasolina C Comum");
        anp.setGroup("Gasolinas");
        anpRepository.save(anp);
    }

    private void addCest() {
        Cest cest = new Cest();
        cest.setSpedCode("00");
        cest.setName("Serviço");
        cestRepository.save(cest);
    }

    private void addItemGender() {
        Gender gender = new Gender();
        gender.setSpedCode("27");
        gender.setName("Combustíveis minerais, óleos minerais e produtos de sua destilação; matérias betuminosas; ceras minerais");
        genderRepository.save(gender);
    }

    private void addCountry() {
        Country country = new Country();
        country.setSpedCode("1058");
        country.setName("BRASIL");
        countryRepository.save(country);
    }

    private void addClassification() {
        Classification cla = new Classification();
        cla.setSpedCode("09");
        cla.setDescription("Outros");
        classificationRepository.save(cla);
    }


    private void addLayoutVersion() {
        LayoutVersion lastVersion = new LayoutVersion();
        lastVersion.setSpedCode("019");
        lastVersion.setStart(LocalDate.of(2025, 1, 1));
        lastVersion.setVersion("1.18");
        layoutVersionRepository.save(lastVersion);
    }

    private void addMunicipality() {
        Municipality municipality = new Municipality();
        municipality.setSpedCode("5300108");
        municipality.setName("Brasília");
        municipalityRepository.save(municipality);

        municipality = new Municipality();
        municipality.setSpedCode("5205497");
        municipality.setName("Cidade Ocidental");
        municipalityRepository.save(municipality);
    }
}
