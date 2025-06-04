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
 * Representação do registro 0450 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0450")
@SpedValidation(validation = "REGRA_REFERENCIADO_COD_INF_17002_V1",
        label = "Informação Complementar", description = "Dados adicionais dos documentos fiscais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0450 {

    public static final String REG = "0450";
    public static final String ID = "B0R0450_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Item.Natureza.spedCode
    @Column(name = "COD_NAT", length = 10, nullable = false)
    private String codNat;

    //Origem Item.Natureza.name
    @Column(name = "DESCR_NAT", length = 300, nullable = false)
    private String descrNat;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}