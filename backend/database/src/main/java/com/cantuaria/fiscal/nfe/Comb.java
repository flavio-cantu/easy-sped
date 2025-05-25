package com.cantuaria.fiscal.nfe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "COM_COMB_ORIGINAL")
public class Comb {
    public static final String ID = "COM_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COM_DS_PROD_ANP")
    @JsonProperty("cProdANP")
    private String cProdANP;

    @Column(name = "COM_DS_DESC_ANP")
    @JsonProperty("descANP")
    private String descANP;

    @Column(name = "COM_DS_UF")
    @JsonProperty("UFCons")
    private String UFCons;

    @Column(name = "COM_VL_BIO")
    @JsonProperty("pBio")
    private Double pBio;

    @Column(name = "COM_NU_BICO")
    @JsonProperty("nBico")
    private Integer nBico;

    @Column(name = "COM_DS_BOMBA")
    @JsonProperty("nBomba")
    private Integer nBomba;

    @Column(name = "COM_NU_TANQUE")
    @JsonProperty("nTanque")
    private Integer nTanque;

    @Column(name = "COM_VL_ENC_INI")
    @JsonProperty("vEncIni")
    private Double vEncIni;

    @Column(name = "COM_VL_ENC_FIN")
    @JsonProperty("vEncFin")
    private Double vEncFin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Det.ID, nullable = false)
    @JsonIgnore
    private Det det;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcProdANP() {
        return cProdANP;
    }

    public void setcProdANP(String cProdANP) {
        this.cProdANP = cProdANP;
    }

    public String getDescANP() {
        return descANP;
    }

    public void setDescANP(String descANP) {
        this.descANP = descANP;
    }

    public String getUFCons() {
        return UFCons;
    }

    public void setUFCons(String UFCons) {
        this.UFCons = UFCons;
    }

    public Double getpBio() {
        return pBio;
    }

    public void setpBio(Double pBio) {
        this.pBio = pBio;
    }

    public Integer getnBico() {
        return nBico;
    }

    public void setnBico(Integer nBico) {
        this.nBico = nBico;
    }

    public Integer getnBomba() {
        return nBomba;
    }

    public void setnBomba(Integer nBomba) {
        this.nBomba = nBomba;
    }

    public Integer getnTanque() {
        return nTanque;
    }

    public void setnTanque(Integer nTanque) {
        this.nTanque = nTanque;
    }

    public Double getvEncIni() {
        return vEncIni;
    }

    public void setvEncIni(Double vEncIni) {
        this.vEncIni = vEncIni;
    }

    public Double getvEncFin() {
        return vEncFin;
    }

    public void setvEncFin(Double vEncFin) {
        this.vEncFin = vEncFin;
    }

    public Det getDet() {
        return det;
    }

    public void setDet(Det det) {
        this.det = det;
    }
}
