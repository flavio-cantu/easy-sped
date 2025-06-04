package com.cantuaria.client;

import com.cantuaria.sped.domain.ActivityType;
import com.cantuaria.sped.domain.UF;
import com.cantuaria.sped.domain.classification.Classification;
import com.cantuaria.sped.domain.converter.ActivityTypeConverter;
import com.cantuaria.sped.domain.municipio.Municipality;
import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe representando os dados do cliente
 */
@Entity
@Table(name = "CLI_CLIENTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    public static final String ID = "CLI_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome empresarial da entidade
     */
    @Column(name = "CLI_DS_NOME_EMPRESA", length = 100, nullable = false)
    private String businessName;

    //TODO criar regra, deve ter pelomenos um: cnpj ou cpf
    @SpedValidation(validation = "REGRA_DIGITO_CNPJ", label = "CNPJ Cliente",
            description = "Número de inscrição da entidade no CNPJ")
    @Column(name = "CLI_DS_CNPJ", length = 14, nullable = false)
    private String cnpj;

    @Column(name = "CLI_DS_NOME_RESPONSAVEL", length = 100, nullable = false)
    private String responsibleName;

    @SpedValidation(validation = "REGRA_DIGITO_CPF", label = "CPF Responsável",
            description = "Número de inscrição da pessoa natural no CPF")
    @Column(name = "CLI_DS_CPF", length = 11, nullable = false)
    private String responsibleCpf;

    @Column(name = "CLI_UF", length = 2, nullable = false, columnDefinition = "varchar(2)")
    @Enumerated(EnumType.STRING)
    private UF uf;

    /**
     * Inscrição Estadual da entidade
     */
    @Column(name = "CLI_DS_INSCRICAO_ESTADUAL", length = 14, nullable = false)
    private String stateRegistration;

    /**
     * Código do município do domicílio fiscal da entidade, conforme a tabela IBGE
     * Infelizmente não temos no sped o vinculo com município. Mas seria bom conseguir.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Municipality.ID, nullable = false)
    private Municipality municipality;

    /**
     * Inscrição Municipal da entidade
     */
    @Column(name = "CLI_DS_INSCRICAO_MUNICIPAL", length = 14)
    private String municipalRegistration;

    @SpedValidation(validation = "REGRA_DIGITO_SUFRAMA", label = "Inscrição na SUFRAMA",
            description = "Inscrição da entidade na SUFRAMA")
    @Column(name = "CLI_DS_SUFRAMA", length = 9)
    private String suframa;

    @Column(name = "CLI_CD_TIPO_ATIVIDADE", length = 1, nullable = false, columnDefinition = "varchar(1)")
    @Convert(converter = ActivityTypeConverter.class)
    private ActivityType activityType;

    /**
     * Nome de fantasia associado ao nome empresarial
     */
    @Column(name = "CLI_DS_NOME_FANTASIA", length = 60, nullable = false)
    private String fantasyName;

    @Column(name = "CLI_DS_CEP", length = 8, nullable = false)
    private String cep;

    @Column(name = "CLI_DS_ENDERECO", length = 60, nullable = false)
    private String address;

    @Column(name = "CLI_DS_NUM", length = 10)
    private String number;

    @Column(name = "CLI_DS_COMPLEMENTO", length = 60)
    private String complement;

    @Column(name = "CLI_DS_BAIRRO", length = 60, nullable = false)
    private String neighborhood;


    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Telefone",
            description = "Número do telefone")
    @Column(name = "CLI_DS_TELEFONE", length = 11)
    private String phone;

    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Fax",
            description = "Número do fax")
    @Column(name = "CLI_DS_FAX", length = 11)
    private String fax;

    @SpedValidation(validation = "REGRA_VALIDA_EMAIL_FISCAL", label = "E-mail",
            description = "Endereço do correio eletrônico")
    @Column(name = "CLI_DS_EMAIL", length = 60)
    private String email;

    /**
     * Informar a classificação do Contribuinte conforme tabela 4.5.5.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Classification.ID, nullable = false)
    private Classification classification;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SubstituteTaxpayer substituteTaxpayer;
}
