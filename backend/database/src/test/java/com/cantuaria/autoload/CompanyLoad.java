package com.cantuaria.autoload;

import com.cantuaria.company.Accountant;
import com.cantuaria.company.AccountantRepository;
import com.cantuaria.company.Company;
import com.cantuaria.company.CompanyRepository;
import com.cantuaria.sped.domain.municipio.Municipality;
import com.cantuaria.sped.domain.municipio.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class CompanyLoad implements CommandLineRunner {

    @Autowired
    private MunicipalityRepository municipalityRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AccountantRepository accountantRepository;


    @Override
    public void run(String... args) throws Exception {
        if(companyRepository.findAll().isEmpty()) {
            Company company = addCompanyData();
            addAccountantData(company);
        }
    }

    private Company addCompanyData() {
        Municipality municipality = municipalityRepository.findById("5300108").get();

        Company company = new Company();
        company.setCnpj("12345678901234");
        company.setCep("71200033");
        company.setAddress("TR SIA TRECHO 3");
        company.setNumber("1530");
        company.setComplement("");
        company.setNeighborhood("ZONA INDUSTRIAL");
        company.setMunicipio(municipality);

        return companyRepository.save(company);
    }

    private void addAccountantData(Company company) {
        Accountant accountant = new Accountant();
        accountant.setName("Contador Teste");
        accountant.setCpf("12345678912");
        accountant.setCrc("DF09969399");
        accountant.setPhone("6188888888");
        accountant.setFax("");
        accountant.setEmail("email@concept.com");
        accountant.setCompany(company);

        accountantRepository.save(accountant);
    }
}
