package com.cantuaria.company;

import com.cantuaria.sped.domain.municipio.Municipality;
import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe com os dados da empresa.
 * Informações necessárias para escrituração
 */
@Entity
@Table(name = "EMP_EMPRESA")
public class Company {
    public static final String ID = "EMP_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SpedValidation(validation = "REGRA_DIGITO_CNPJ", label = "CNPJ Empresa",
            description = "Número de inscrição do escritório de contabilidade no CNPJ")
    @Column(name = "EMP_DS_CNPJ", length = 14, nullable = false)
    private String cnpj;

    @Column(name = "EMP_DS_CEP", length = 8)
    private String cep;

    @Column(name = "EMP_DS_ENDERECO", length = 60)
    private String address;

    @Column(name = "EMP_DS_NUM", length = 10)
    private String number;

    @Column(name = "EMP_DS_COMPLEMENTO", length = 60)
    private String complement;

    @Column(name = "EMP_DS_BAIRRO", length = 60, nullable = false)
    private String neighborhood;

    /**
     * Código do município do domicílio fiscal da entidade, conforme a tabela IBGE
     * Infelizmente não temos no sped o vinculo com município. Mas seria bom conseguir.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Municipality.ID, nullable = false)
    private Municipality municipality;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Accountant> accountants;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Municipality getMunicipio() {
        return municipality;
    }

    public void setMunicipio(Municipality municipality) {
        this.municipality = municipality;
    }

    public List<Accountant> getAccountants() {
        return accountants;
    }

    public void setAccountants(List<Accountant> accountants) {
        this.accountants = accountants;
    }
}
