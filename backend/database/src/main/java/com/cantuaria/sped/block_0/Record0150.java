package com.cantuaria.sped.block_0;

import com.cantuaria.sped.domain.country.CountryRepository;
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
 * Representação do registro 0150 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0150")
@SpedValidation(validation = {
        "REGRA_NAO_PREENCHER_BRA_CNPJ",
        "REGRA_NAO_PREENCHER_BRA_CPF",
        "REGRA_NAO_PREENCHER_BRA_IE",
        "REGRA_NAO_PREENCHER_BRA_SUFRAMA",
        "REGRA_CNPJ_EXCLUDENTE",
        "REGRA_OBRIGATORIO_CPF_BRA_V2",
        "REGRA_OBRIGATORIO_PARA_BRASIL_A10E3V4",
        "REGRA_REFERENCIADO_COD_PART_17002_V1",
        "REGRA_VALIDA_IE_COD_MUN_V2"
}, label = "Participante",
        description = "Clientes, Fornecedores e Outros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0150 {

    public static final String REG = "0150";
    public static final String ID = "B0R0150_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Participant.code
    @Column(name = "COD_PART", length = 60, nullable = false)
    private String codPart;

    //Origem Participant.name
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    //Origem Participant.country
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = CountryRepository.class,
            label = "Código do país", description = "Código do país do participante, conforme a tabela Órgãos mantenedores e endereços eletrônicos das tabelas externas - Tabela de Países")
    @Column(name = "COD_PAIS", length = 5, nullable = false)
    private String codPais;

    //Origem Participant.cnpj
    @SpedValidation(validation = "REGRA_DIGITO_CNPJ", label = "CNPJ Participante",
            description = "CNPJ do participante")
    @Column(name = "CNPJ", length = 14)
    private String cnpj;

    //Origem Participant.cpf
    @SpedValidation(validation = "REGRA_DIGITO_CPF", label = "CPF",
            description = "CPF do participante")
    @Column(name = "CPF", length = 11)
    private String cpf;

    //Origem Participant.stateRegistration
    @Column(name = "IE", length = 14)
    private String inscricaoEstadual;

    //Origem Participant.municipality
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = MunicipalityRepository.class,
            label = "Codigo do município", description = "Código do município, conforme tabela IBGE")
    @Column(name = "COD_MUN", length = 7)
    private String codMun;

    //Origem Participant.suframa
    @SpedValidation(validation = "REGRA_DIGITO_SUFRAMA", label = "Inscrição na SUFRAMA",
            description = "Inscrição da entidade na SUFRAMA")
    @Column(name = "SUFRAMA", length = 9)
    private String suframa;

    //Origem Participant.address
    @Column(name = "ENDERECO", length = 60, nullable = false)
    private String endereco;

    //Origem Participant.number
    @Column(name = "NUM", length = 10)
    private String num;

    //Origem Participant.complement
    @Column(name = "COMPL", length = 60)
    private String compl;

    //Origem Participant.neighborhood
    @Column(name = "BAIRRO", length = 60, nullable = false)
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}