package com.cantuaria.sped.block_0;

import com.cantuaria.item.UnityRepository;
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
 * Representação do registro 0220 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0220")
@SpedValidation(validation = {
        "REGRA_EXISTE_UNID_V2",
        "REGRA_VALIDA_COD_BARRA",
        "REGRA_BATIMENTO_COD_BARRA_V2",
}, label = "Fatores de Conversão de Unidades", description = "Fatores de Conversão de Unidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0220 {

    public static final String REG = "0220";
    public static final String ID = "B0R0220_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Item.conversionUnity
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = UnityRepository.class,
            label = "Unidade comercial", description = "Unidade comercial a ser convertida na unidade de estoque, referida no registro 0200")
    @Column(name = "UNID_CONV", length = 6, nullable = false)
    private String unidConversao;

    //Origem Item.conversionFactor
    @SpedValidation(validation = "REGRA_MAIOR_ZERO", label = "Fator de conversão",
            description = "Fator de conversão: fator utilizado para converter (multiplicar) a unidade a ser convertida na unidade adotada no inventário")
    @Column(name = "FAT_CONV", length = 6, precision = 2, nullable = false)
    private Double fatConv;

    //Origem Item.representationCode
    @Column(name = "COD_BARRA", length = 20)
    private String codBarra;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Record0200.ID, nullable = false)
    private Record0200 record0200;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}