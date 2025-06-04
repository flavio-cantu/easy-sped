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
 * Representação do registro 0190 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0190")
@SpedValidation(validation = "REGRA_REFERENCIADO_UNID_15001V1",
        label = "Identificação das Unidades de Medida", description = "Identificação das Unidades de Medida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0190 {

    public static final String REG = "0190";
    public static final String ID = "B0R0190_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Unity.code
    @Column(name = "UNID", length = 6, nullable = false)
    private String unid;

    //Origem Unity.name
    @Column(name = "DESCR", length = 15, nullable = false)
    private String descr;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}