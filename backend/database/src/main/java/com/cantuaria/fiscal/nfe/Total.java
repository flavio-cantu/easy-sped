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
@Table(name = "TNO_TOTAL_NF_ORIGINAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Total {
    public static final String ID = "TNO_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TNO_VL_V_BC")
    @JsonProperty("vBC")
    private Double vBC;

    @Column(name = "TNO_VL_V_ICMS")
    @JsonProperty("vICMS")
    private Double vICMS;

    @Column(name = "TNO_VL_V_ICMS_DESON")
    @JsonProperty("vICMSDeson")
    private Double vICMSDeson;

    @Column(name = "TNO_VL_V_FCP")
    @JsonProperty("vFCP")
    private Double vFCP;

    @Column(name = "TNO_VL_V_BCST")
    @JsonProperty("vBCST")
    private Double vBCST;

    @Column(name = "TNO_VL_V_ST")
    @JsonProperty("vST")
    private Double vST;

    @Column(name = "TNO_VL_V_FCPST")
    @JsonProperty("vFCPST")
    private Double vFCPST;

    @Column(name = "TNO_VL_V_FCPS_RET")
    @JsonProperty("vFCPSTRet")
    private Double vFCPSTRet;

    @Column(name = "TNO_VL_Q_BC_MONO_RET")
    @JsonProperty("qBCMonoRet")
    private Double qBCMonoRet;

    @Column(name = "TNO_VL_V_ICMS_MONO_RET")
    @JsonProperty("vICMSMonoRet")
    private Double vICMSMonoRet;

    @Column(name = "TNO_VL_V_PROD")
    @JsonProperty("vProd")
    private Double vProd;

    @Column(name = "TNO_VL_V_FRETE")
    @JsonProperty("vFrete")
    private Double vFrete;

    @Column(name = "TNO_VL_V_SEG")
    @JsonProperty("vSeg")
    private Double vSeg;

    @Column(name = "TNO_VL_V_DESC")
    @JsonProperty("vDesc")
    private Double vDesc;

    @Column(name = "TNO_VL_V_II")
    @JsonProperty("vII")
    private Double vII;

    @Column(name = "TNO_VL_V_IPI")
    @JsonProperty("vIPI")
    private Double vIPI;

    @Column(name = "TNO_VL_V_IPI_DEVOL")
    @JsonProperty("vIPIDevol")
    private Double vIPIDevol;

    @Column(name = "TNO_VL_V_PIS")
    @JsonProperty("vPIS")
    private Double vPIS;

    @Column(name = "TNO_VL_V_COFINS")
    @JsonProperty("vCOFINS")
    private Double vCOFINS;

    @Column(name = "TNO_VL_V_OUTRO")
    @JsonProperty("vOutro")
    private Double vOutro;

    @Column(name = "TNO_VL_V_NF")
    @JsonProperty("vNF")
    private Double vNF;

    @Column(name = "TNO_VL_V_TOT_TRIB")
    @JsonProperty("vTotTrib")
    private Double vTotTrib;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = NotaFiscal.ID, nullable = false)
    @JsonIgnore
    private NotaFiscal notaFiscal;

}
