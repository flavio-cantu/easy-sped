package com.cantuaria.fiscal.nfe;

import com.cantuaria.fiscal.NotaFiscal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "EMO_EMIT_ORIGINAL")
public class Emit {
    public static final String ID = "EMO_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "EMO_DS_CNPJ")
    @JsonProperty("CNPJ")
    private String CNPJ;

    @Column(name = "EMO_DS_NOME")
    @JsonProperty("xNome")
    private String xNome;

    @Column(name = "EMO_DS_FANTASIA")
    @JsonProperty("xFant")
    private String xFant;

    @Column(name = "EMO_DS_IE", length = 14)
    @JsonProperty("IE")
    private String inscricaoEstadual;

    @Column(name = "EMO_DS_CRT")
    @JsonProperty("CRT")
    private String CRT;

    //Dados de endereço ===========

    @Column(name = "EMO_DS_LOGRADOURO")
    @JsonProperty("xLgr")
    private String xLgr;

    @Column(name = "EMO_DS_NUMERO")
    @JsonProperty("nro")
    private String nro;

    @Column(name = "EMO_DS_BAIRRO")
    @JsonProperty("xBairro")
    private String xBairro;

    @Column(name = "EMO_DS_COD_MUNICIPIO")
    @JsonProperty("cMun")
    private String cMun;

    @Column(name = "EMO_DS_NOME_MUNICIPIO")
    @JsonProperty("xMun")
    private String xMun;

    @Column(name = "EMO_DS_UF")
    @JsonProperty("UF")
    private String UF;

    @Column(name = "EMO_DS_CEP")
    @JsonProperty("CEP")
    private String CEP;

    @Column(name = "EMO_DS_COD_PAIS")
    @JsonProperty("cPais")
    private String cPais;

    @Column(name = "EMO_DS_NOME_PAIS")
    @JsonProperty("xPais")
    private String xPais;

    @Column(name = "EMO_DS_TELEFONE")
    @JsonProperty("fone")
    private String fone;

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

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getxFant() {
        return xFant;
    }

    public void setxFant(String xFant) {
        this.xFant = xFant;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCRT() {
        return CRT;
    }

    public void setCRT(String CRT) {
        this.CRT = CRT;
    }

    public String getxLgr() {
        return xLgr;
    }

    public void setxLgr(String xLgr) {
        this.xLgr = xLgr;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getxBairro() {
        return xBairro;
    }

    public void setxBairro(String xBairro) {
        this.xBairro = xBairro;
    }

    public String getcMun() {
        return cMun;
    }

    public void setcMun(String cMun) {
        this.cMun = cMun;
    }

    public String getxMun() {
        return xMun;
    }

    public void setxMun(String xMun) {
        this.xMun = xMun;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getcPais() {
        return cPais;
    }

    public void setcPais(String cPais) {
        this.cPais = cPais;
    }

    public String getxPais() {
        return xPais;
    }

    public void setxPais(String xPais) {
        this.xPais = xPais;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
}
