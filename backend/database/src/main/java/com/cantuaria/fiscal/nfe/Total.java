package com.cantuaria.fiscal.nfe;

import com.cantuaria.fiscal.NotaFiscal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "TNO_TOTAL_NF_ORIGINAL")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getvBC() {
        return vBC;
    }

    public void setvBC(Double vBC) {
        this.vBC = vBC;
    }

    public Double getvICMS() {
        return vICMS;
    }

    public void setvICMS(Double vICMS) {
        this.vICMS = vICMS;
    }

    public Double getvICMSDeson() {
        return vICMSDeson;
    }

    public void setvICMSDeson(Double vICMSDeson) {
        this.vICMSDeson = vICMSDeson;
    }

    public Double getvFCP() {
        return vFCP;
    }

    public void setvFCP(Double vFCP) {
        this.vFCP = vFCP;
    }

    public Double getvBCST() {
        return vBCST;
    }

    public void setvBCST(Double vBCST) {
        this.vBCST = vBCST;
    }

    public Double getvST() {
        return vST;
    }

    public void setvST(Double vST) {
        this.vST = vST;
    }

    public Double getvFCPST() {
        return vFCPST;
    }

    public void setvFCPST(Double vFCPST) {
        this.vFCPST = vFCPST;
    }

    public Double getvFCPSTRet() {
        return vFCPSTRet;
    }

    public void setvFCPSTRet(Double vFCPSTRet) {
        this.vFCPSTRet = vFCPSTRet;
    }

    public Double getqBCMonoRet() {
        return qBCMonoRet;
    }

    public void setqBCMonoRet(Double qBCMonoRet) {
        this.qBCMonoRet = qBCMonoRet;
    }

    public Double getvICMSMonoRet() {
        return vICMSMonoRet;
    }

    public void setvICMSMonoRet(Double vICMSMonoRet) {
        this.vICMSMonoRet = vICMSMonoRet;
    }

    public Double getvProd() {
        return vProd;
    }

    public void setvProd(Double vProd) {
        this.vProd = vProd;
    }

    public Double getvFrete() {
        return vFrete;
    }

    public void setvFrete(Double vFrete) {
        this.vFrete = vFrete;
    }

    public Double getvSeg() {
        return vSeg;
    }

    public void setvSeg(Double vSeg) {
        this.vSeg = vSeg;
    }

    public Double getvDesc() {
        return vDesc;
    }

    public void setvDesc(Double vDesc) {
        this.vDesc = vDesc;
    }

    public Double getvII() {
        return vII;
    }

    public void setvII(Double vII) {
        this.vII = vII;
    }

    public Double getvIPI() {
        return vIPI;
    }

    public void setvIPI(Double vIPI) {
        this.vIPI = vIPI;
    }

    public Double getvIPIDevol() {
        return vIPIDevol;
    }

    public void setvIPIDevol(Double vIPIDevol) {
        this.vIPIDevol = vIPIDevol;
    }

    public Double getvPIS() {
        return vPIS;
    }

    public void setvPIS(Double vPIS) {
        this.vPIS = vPIS;
    }

    public Double getvCOFINS() {
        return vCOFINS;
    }

    public void setvCOFINS(Double vCOFINS) {
        this.vCOFINS = vCOFINS;
    }

    public Double getvOutro() {
        return vOutro;
    }

    public void setvOutro(Double vOutro) {
        this.vOutro = vOutro;
    }

    public Double getvNF() {
        return vNF;
    }

    public void setvNF(Double vNF) {
        this.vNF = vNF;
    }

    public Double getvTotTrib() {
        return vTotTrib;
    }

    public void setvTotTrib(Double vTotTrib) {
        this.vTotTrib = vTotTrib;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
}
