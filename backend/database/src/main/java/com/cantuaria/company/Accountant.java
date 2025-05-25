package com.cantuaria.company;

import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.*;

/**
 * Classe com os dados do contador.
 * Informações necessárias para escrituração
 */
@Entity
@Table(name = "CON_CONTABILISTA")
public class Accountant {
    public static final String ID = "CON_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CON_DS_NOME", length = 60, nullable = false)
    private String name;

    @SpedValidation(validation = "REGRA_DIGITO_CPF", label = "CPF",
            description = "Número de inscrição do contabilista no CPF")
    @Column(name = "CON_DS_CPF", length = 11, nullable = false)
    private String cpf;

    @Column(name = "CON_DS_CRC", length = 15, nullable = false)
    private String crc;

    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Telefone",
            description = "Número do telefone")
    @Column(name = "CON_DS_TELEFONE", length = 11)
    private String phone;

    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Fax",
            description = "Número do fax")
    @Column(name = "CON_DS_FAX", length = 11)
    private String fax;

    @SpedValidation(validation = "REGRA_VALIDA_EMAIL_FISCAL", label = "E-mail",
            description = "Endereço do correio eletrônico")
    @Column(name = "CON_DS_EMAIL", length = 60)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Company.ID, nullable = false)
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
