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
 * Representação do registro 0460 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0460")
@SpedValidation(validation = "REGRA_REFERENCIADO_COD_INF_17002_V1",
        label = "Observação do Lanc. Fiscal", description = "Dados adicionais da apuração")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0460 {

    public static final String REG = "0460";
    public static final String ID = "B0R0460_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Bookkeeping.Observation.code
    @Column(name = "COD_OBS", length = 4, nullable = false)
    private String vodObs;

    //Origem Bookkeeping.Observation.description
    @Column(name = "TXT", length = 300, nullable = false)
    private String txt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}