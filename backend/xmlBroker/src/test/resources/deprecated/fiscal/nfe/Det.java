package com.cantuaria.fiscal.nfe;

import com.cantuaria.fiscal.NotaFiscal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "DEO_DET_ORIGINAL")
public class Det {

    public static final String ID = "DEO_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nItem;
    private String cProd;
    private String cEAN;
    private String xProd;
    @JsonProperty("NCM")
    private String NCM;
    private String cBenef;
    @JsonProperty("CFOP")
    private String CFOP;
    private String uCom;
    private Double qCom;
    private Double vUnCom;
    private Double vProd;
    private String cEANTrib;
    private String uTrib;
    private Double qTrib;
    private Double vUnTrib;
    private Double indTot;
    private Double vDesc;

    @OneToOne(mappedBy = "det",cascade = CascadeType.ALL)
    private Comb comb;

    @OneToOne(mappedBy = "det",cascade = CascadeType.ALL)
    private Imposto imposto;

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

    public String getnItem() {
        return nItem;
    }

    public void setnItem(String nItem) {
        this.nItem = nItem;
    }

    public String getcProd() {
        return cProd;
    }

    public void setcProd(String cProd) {
        this.cProd = cProd;
    }

    public String getcEAN() {
        return cEAN;
    }

    public void setcEAN(String cEAN) {
        this.cEAN = cEAN;
    }

    public String getxProd() {
        return xProd;
    }

    public void setxProd(String xProd) {
        this.xProd = xProd;
    }

    public String getNCM() {
        return NCM;
    }

    public void setNCM(String NCM) {
        this.NCM = NCM;
    }

    public String getcBenef() {
        return cBenef;
    }

    public void setcBenef(String cBenef) {
        this.cBenef = cBenef;
    }

    public String getCFOP() {
        return CFOP;
    }

    public void setCFOP(String CFOP) {
        this.CFOP = CFOP;
    }

    public String getuCom() {
        return uCom;
    }

    public void setuCom(String uCom) {
        this.uCom = uCom;
    }

    public Double getqCom() {
        return qCom;
    }

    public void setqCom(Double qCom) {
        this.qCom = qCom;
    }

    public Double getvUnCom() {
        return vUnCom;
    }

    public void setvUnCom(Double vUnCom) {
        this.vUnCom = vUnCom;
    }

    public Double getvProd() {
        return vProd;
    }

    public void setvProd(Double vProd) {
        this.vProd = vProd;
    }

    public String getcEANTrib() {
        return cEANTrib;
    }

    public void setcEANTrib(String cEANTrib) {
        this.cEANTrib = cEANTrib;
    }

    public String getuTrib() {
        return uTrib;
    }

    public void setuTrib(String uTrib) {
        this.uTrib = uTrib;
    }

    public Double getqTrib() {
        return qTrib;
    }

    public void setqTrib(Double qTrib) {
        this.qTrib = qTrib;
    }

    public Double getvUnTrib() {
        return vUnTrib;
    }

    public void setvUnTrib(Double vUnTrib) {
        this.vUnTrib = vUnTrib;
    }

    public Double getIndTot() {
        return indTot;
    }

    public void setIndTot(Double indTot) {
        this.indTot = indTot;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Comb getComb() {
        return comb;
    }

    public void setComb(Comb comb) {
        this.comb = comb;
    }

    public Imposto getImposto() {
        return imposto;
    }

    public void setImposto(Imposto imposto) {
        this.imposto = imposto;
    }

    public Double getvDesc() {
        return vDesc;
    }

    public void setvDesc(Double vDesc) {
        this.vDesc = vDesc;
    }
}