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
 * Representação do registro 0205 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0205")
@SpedValidation(validation = {
        "REGRA_DATA_MENOR_IGUAL_DT_FIN_DT_INI_DT_FIM",
        "REGRA_DATA_MENOR_DT_FIN_0000",
        "REGRA_NAO_INFORMAR_COD_ANT_ITEM",
        "REGRA_OBRIGATORIO_COD_ANT_ITEM",
        "REGRA_NAO_PERMITIR_SOBREPOSICAO_0205_CORRECAO"
}, label = "Alterações", description = "Alterações do item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0205 {

    public static final String REG = "0205";
    public static final String ID = "B0R0205_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem ItemHistory.previousDescription
    @Column(name = "DESCR_ANT_ITEM", length = 200)
    private String descrAntItem;

    //Origem ItemHistory.startDate
    @SpedValidation(validation = {"REGRA_DATA_VALIDA_A4E5V1", "REGRA_DATA_MINIMA_DOC_FISCAL"}, label = "Data inicial",
            description = "Data inicial de utilização da descrição do item")
    @Column(name = "DT_INI", length = 8, nullable = false)
    private String dtIni;

    //Origem ItemHistory.endDate
    @SpedValidation(validation = {"REGRA_DATA_VALIDA_A4E5V1", "REGRA_DATA_MINIMA_DOC_FISCAL"}, label = "Data final",
            description = "Data final de utilização da descrição do item")
    @Column(name = "DT_FIM", length = 8, nullable = false)
    private String dtFim;

    //Origem ItemHistory.previousCode
    @Column(name = "COD_ANT_ITEM", length = 60)
    private String codAntItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Record0200.ID, nullable = false)
    private Record0200 record0200;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}