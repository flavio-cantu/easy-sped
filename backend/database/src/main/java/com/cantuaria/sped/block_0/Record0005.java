package com.cantuaria.sped.block_0;

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
 * Representação do registro 0005 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0005")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0005 {

    public static final String REG = "0005";
    public static final String ID = "B0R0005_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Client.fantasyName
    @Column(name = "FANTASIA", length = 60, nullable = false)
    private String fantasia;

    //Origem Client.cep
    @Column(name = "CEP", length = 8, nullable = false)
    private String cep;

    //Origem Client.address
    @Column(name = "ENDERECO", length = 60, nullable = false)
    private String endereco;

    //Origem Client.number
    @Column(name = "NUM", length = 10)
    private String num;

    //Origem Client.complement
    @Column(name = "COMPL", length = 60)
    private String compl;

    //Origem Client.neighborhood
    @Column(name = "BAIRRO", length = 60, nullable = false)
    private String bairro;

    //Origem Client.phone
    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Telefone",
            description = "Número do telefone")
    @Column(name = "FONE", length = 11)
    private String fone;

    //Origem Client.fax
    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Fax",
            description = "Número do fax")
    @Column(name = "FAX", length = 11)
    private String fax;

    //Origem Client.fax
    @SpedValidation(validation = "REGRA_VALIDA_EMAIL_FISCAL", label = "E-mail",
            description = "Endereço do correio eletrônico")
    @Column(name = "EMAIL", length = 60)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}