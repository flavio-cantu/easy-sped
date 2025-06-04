package com.cantuaria.sped.block_0;

import com.cantuaria.item.ItemType;
import com.cantuaria.item.UnityRepository;
import com.cantuaria.sped.domain.cest.CestRepository;
import com.cantuaria.sped.domain.gender.GenderRepository;
import com.cantuaria.validation.SpedDatabaseValidation;
import com.cantuaria.validation.SpedEnumValidation;
import com.cantuaria.validation.SpedInnerObject;
import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Representação do registro 0200 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0200")
@SpedValidation(validation = {
        "REGRA_EXISTE_UNID_V2",
        "REGRA_OBRIGATORIO_ALIQ_RESSARC_ST",
        "REGRA_REFERENCIADO_COD_ITEM_17002_V1",
        "REGRA_OBRIGATORIO_NCM",
        "REGRA_VALIDA_COD_BARRA",
        "REGRA_OBRIGATORIO_COD_BARRA",
}, label = "Item/Produtos", description = "Produtos, Serviços e Outros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0200 {

    public static final String REG = "0200";
    public static final String ID = "B0R0200_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Item.itemCode
    @Column(name = "COD_ITEM", length = 60, nullable = false)
    private String codItem;

    //Origem Item.description
    @Column(name = "DESCR_ITEM", length = 200, nullable = false)
    private String descrItem;

    //Origem Item.barCode
    @Column(name = "COD_BARRA", length = 20)
    private String codBarra;

    //Sem origem, o preenchimento só é obrigatório se o código do item mudar
    @SpedValidation(validation = "REGRA_NAO_INFORMAR_COD_ANT_ITEM_0200", label = "Código anterior do item",
            description = "Código anterior do item com relação à última informação apresentada")
    @Column(name = "COD_ANT_ITEM", length = 60)
    private String codAntItem;

    //Origem Item.unity
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = UnityRepository.class,
            label = "Unidade de medida", description = "Unidade de medida utilizada na quantificação de estoques")
    @Column(name = "UNID_INV", length = 6, nullable = false)
    private String unidInv;

    //Origem Item.itemType
    @SpedEnumValidation(validation = "CODIGO_EXISTE_ENUM", enumType = ItemType.class,
            label = "Tipo do item", description = "Tipo do item – Atividades Industriais, Comerciais e Serviços")
    @Column(name = "TIPO_ITEM", length = 2, nullable = false)
    private String tipoItem;

    //Origem Item.mercosulCode
    @SpedValidation(validation = "REGRA_VALIDA_COD_NCM", label = "Código NCM",
            description = "Código da Nomenclatura Comum do Mercosul")
    @Column(name = "COD_NCM", length = 8)
    private String codNCM;

    @Column(name = "EX_IPI", length = 3)
    private String exIPI;

    //Origem Item.gender
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = GenderRepository.class,
            label = "Código do gênero do item", description = "Código do gênero do item, conforme a Tabela Gênero do Item de Mercadoria/Serviço")
    @Column(name = "COD_GEN", length = 2)
    private String codGen;

    //Origem Item.serviceCode
    @SpedValidation(validation = "REGRA_VALIDA_COD_LST", label = "Código do serviço do item",
            description = "Código do serviço, conforme lista do Anexo I da Lei Complementar Federal nº 116/03")
    @Column(name = "COD_LST", length = 5)
    private String codLST;

    //Origem Item.aliquotICMS
    @SpedValidation(validation = "REGRA_ALIQ_MENOR_1000", label = "Alíquota do ICMS(%)",
            description = "Alíquota de ICMS aplicável ao item nas operações internas")
    @Column(name = "ALIQ_ICMS", length = 6, precision = 2)
    private Double aliqICMS;

    //Origem Item.cest
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = CestRepository.class,
            label = "Código Especificador de Substituição Tribuitária", description = "Código Especificador de Substituição Tribuitária")
    @Column(name = "CEST", length = 7)
    private String cest;

    @OneToMany(mappedBy = "record0200", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject
    //Pode ser obrigatório se houver alteraçẽos no item
    private List<Record0205> records0205;

    @OneToOne(mappedBy = "record0200", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject
    //Pode ser obrigatório por regra interna
    private Record0220 record0220;

    @OneToOne(mappedBy = "record0200", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject
    //Pode ser obrigatório por regra interna
    private Record0221 record0221;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}