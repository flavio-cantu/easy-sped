package com.cantuaria.sped.block_0;

import com.cantuaria.sped.domain.municipio.MunicipalityRepository;
import com.cantuaria.validation.SpedDatabaseValidation;
import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do registro 0100 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0100")
@SpedValidation(validation = "REGRA_REGISTRO_OBRIGATORIO_0100", label = "Contabilista",
        description = "Dados do Contabilista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0100 {

    public static final String REG = "0100";
    public static final String ID = "B0R0100_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Accountant.name
    @Column(name = "NOME", length = 60, nullable = false)
    private String nome;

    //Origem Accountant.cpf
    @SpedValidation(validation = "REGRA_DIGITO_CPF", label = "CPF",
            description = "Número de inscrição do contabilista no CPF")
    @Column(name = "CPF", length = 11, nullable = false)
    private String cpf;

    //Origem Accountant.crc
    @Column(name = "CRC", length = 15, nullable = false)
    private String crc;

    //Origem Company.cnpj
    @SpedValidation(validation = "REGRA_DIGITO_CNPJ", label = "CNPJ Empresa",
            description = "Número de inscrição do escritório de contabilidade no CNPJ")
    @Column(name = "CNPJ", length = 14)
    private String cnpj;

    //Origem Company.cep
    @Column(name = "CEP", length = 8)
    private String cep;

    //Origem Company.address
    @Column(name = "ENDERECO", length = 60)
    private String endereco;

    //Origem Company.number
    @Column(name = "NUM", length = 10)
    private String num;

    //Origem Company.complement
    @Column(name = "COMPL", length = 60)
    private String complement;

    //Origem Company.neighborhood
    @Column(name = "BAIRRO", length = 60)
    private String bairro;

    //Origem Accountant.phone
    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Telefone",
            description = "Número do telefone")
    @Column(name = "FONE", length = 11)
    private String fone;

    //Origem Accountant.fax
    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Fax",
            description = "Número do fax")
    @Column(name = "FAX", length = 11)
    private String fax;

    //Origem Accountant.email
    @SpedValidation(validation = "REGRA_VALIDA_EMAIL_FISCAL", label = "E-mail",
            description = "Endereço do correio eletrônico")
    @Column(name = "EMAIL", length = 60, nullable = false)
    private String email;


    //Origem Company.municipality
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = MunicipalityRepository.class,
            label = "Codigo do município", description = "Código do município, conforme tabela IBGE")
    @Column(name = "COD_MUN", length = 7, nullable = false)
    private String codMun;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}