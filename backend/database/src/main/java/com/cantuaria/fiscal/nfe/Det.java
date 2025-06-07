package com.cantuaria.fiscal.nfe;

import com.cantuaria.fiscal.NotaFiscal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DEO_DET_ORIGINAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Det {

    public static final String ID = "DEO_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DEO_DS_N_ITEM")
    @JsonProperty("nItem")
    private String nItem;

    @Column(name = "DEO_DS_C_PROD")
    @JsonProperty("cProd")
    private String cProd;

    @Column(name = "DEO_DS_C_EAN")
    @JsonProperty("cEAN")
    private String cEAN;

    @Column(name = "DEO_DS_X_PROD")
    @JsonProperty("xProd")
    private String xProd;

    @Column(name = "DEO_DS_NCM")
    @JsonProperty("NCM")
    private String NCM;

    @Column(name = "DEO_DS_C_BENEF")
    @JsonProperty("cBenef")
    private String cBenef;

    @Column(name = "DEO_DS_CFOP")
    @JsonProperty("CFOP")
    private String CFOP;

    @Column(name = "DEO_DS_U_COM")
    @JsonProperty("uCom")
    private String uCom;

    @Column(name = "DEO_VL_Q_COM")
    @JsonProperty("qCom")
    private Double qCom;

    @Column(name = "DEO_VL_V_UN_COM")
    @JsonProperty("vUnCom")
    private Double vUnCom;

    @Column(name = "DEO_VL_V_PROD")
    @JsonProperty("vProd")
    private Double vProd;

    @Column(name = "DEO_DS_C_EAN_TRIB")
    @JsonProperty("cEANTrib")
    private String cEANTrib;

    @Column(name = "DEO_DS_U_TRIB")
    @JsonProperty("uTrib")
    private String uTrib;

    @Column(name = "DEO_VL_Q_TRIB")
    @JsonProperty("qTrib")
    private Double qTrib;

    @Column(name = "DEO_VL_UN_TRIB")
    @JsonProperty("vUnTrib")
    private Double vUnTrib;

    @Column(name = "DEO_VL_IND_TOT")
    @JsonProperty("indTot")
    private Double indTot;

    @Column(name = "DEO_VL_V_DESC")
    @JsonProperty("vDesc")
    private Double vDesc;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Comb comb;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Imposto imposto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = NotaFiscal.ID, nullable = false)
    @JsonIgnore
    private NotaFiscal notaFiscal;

}