package com.cantuaria.item;

import com.cantuaria.sped.domain.additional_information.AdditionalInformation;
import com.cantuaria.sped.domain.anp.Anp;
import com.cantuaria.sped.domain.cest.Cest;
import com.cantuaria.sped.domain.gender.Gender;
import com.cantuaria.sped.domain.nature.Nature;
import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Item no EDF é a representação de Produtos, Serviços e Outros
 * TODO alerta de carga!
 * Assim como participantes, vamos precisar carregar as informações do sistema
 */

@Entity
@Table(name = "ITE_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    public static final String ID = "ITE_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ITE_CD", length = 60, nullable = false)
    private String itemCode;

    //TODO Vincular o item do cadstro ao item da nota
    /*
    cProd é o código do produto no sistema emissor da nota
    Mas como ele varia por sistema, também temos o caso de vários postos
    utilizando o mesmo cnpj como cada posto usando um cnpj diferente, mas todos
    filhos da mesma empresa
     */


    @Column(name = "ITE_DS", length = 200, nullable = false)
    private String description;

    @Column(name = "ITE_CD_BARRA", length = 20)
    private String barCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UME_ID_ITEM", nullable = false)
    private Unity unity;

    @Column(name = "ITE_CD_TIPO", length = 2, nullable = false, columnDefinition = "varchar(2)")
    @Convert(converter = ItemTypeConverter.class)
    private ItemType itemType;

    @SpedValidation(validation = "REGRA_VALIDA_COD_NCM", label = "Código NCM",
            description = "Código da Nomenclatura Comum do Mercosul")
    @Column(name = "ITE_CD_MERCOSUL", length = 8)
    private String mercosulCode;

    @Column(name = "ITE_CD_EX_TIPI", length = 3)
    private String exTipiCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Gender.ID, nullable = false)
    private Gender gender;


    @SpedValidation(validation = "REGRA_VALIDA_COD_LST", label = "Código do serviço do item",
            description = "Código do serviço, conforme lista do Anexo I da Lei Complementar Federal nº 116/03")
    @Column(name = "ITE_CD_SERVICE", length = 5)
    private String serviceCode;

    @SpedValidation(validation = "REGRA_ALIQ_MENOR_1000", label = "Alíquota do ICMS(%)",
            description = "Alíquota de ICMS aplicável ao item nas operações internas")
    @Column(name = "ITE_VL_ALIQUOTA_ICMS", length = 6, precision = 2)
    private Double aliquotICMS;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Cest.ID, nullable = false)
    private Cest cest;

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemHistory> histories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Anp.ID, nullable = false)
    private Anp anp;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UME_ID_CONVERSAO", nullable = false)
    private Unity conversionUnity;

    @SpedValidation(validation = "REGRA_MAIOR_ZERO", label = "Fator de conversão",
            description = "Fator de conversão: fator utilizado para converter (multiplicar) a unidade a ser convertida na unidade adotada no inventário")
    @Column(name = "ITE_VL_FATOR_CONVERSAO", length = 6, precision = 2, nullable = false)
    private Double conversionFactor;

    @Column(name = "ITE_DS_CODIGO_REPRESENTACAO", length = 20)
    private String representationCode;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "INA_ITEM_NATUREZA",
            joinColumns = @JoinColumn(name = Item.ID),
            inverseJoinColumns = @JoinColumn(name = Nature.ID)
    )
    private List<Nature> natures = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "IIC_ITEM_INFORMACAO_COMPLEMENTAR",
            joinColumns = @JoinColumn(name = Item.ID),
            inverseJoinColumns = @JoinColumn(name = AdditionalInformation.ID)
    )
    private List<AdditionalInformation> informations = new ArrayList<>();

}
