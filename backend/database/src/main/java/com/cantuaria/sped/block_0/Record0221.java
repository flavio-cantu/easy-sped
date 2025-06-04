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

import java.math.BigDecimal;

/**
 * Representação do registro 0221 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 * <p>
 * TODO Este item é afetado pelo lançamento contábil
 * Se no lançamento contábil (ex.: Registro 0200) a unidade usada não for a mesma do 0150, o 0221 deve ser gerado.
 * Esse registro é obrigatório apenas se houver movimentação em unidade diferente.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0221")
@SpedValidation(validation = {
        "REGRA_EXISTE_COD_ITEM_V2",
        "REGRA_VALIDA_COD_ITEM_ATOMICO_V2",
        "REGRA_INFORMAR_0221",
}, label = "Correlação entre Itens", description = "Correlação entre Códigos de Itens Comercializados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0221 {

    public static final String REG = "0221";
    public static final String ID = "B0R0221_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem calculada
    @Column(name = "COD_ITEM_ATOMICO", length = 60, nullable = false)
    private String codItemAtomico;

    //Origem calculada
    @SpedValidation(validation = {
            "REGRA_MAIOR_IGUAL_UM",
            "REGRA_QTD_IGUAL_UM"
    }, label = "Quantidade de itens atômicos",
            description = "Informar quantos itens atômicos estão contidos no item informado no 0200 Pai")
    @Column(name = "QTD_CONTIDA", length = 6, precision = 6, nullable = false)
    private BigDecimal qntContida;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Record0200.ID, nullable = false)
    private Record0200 record0200;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}