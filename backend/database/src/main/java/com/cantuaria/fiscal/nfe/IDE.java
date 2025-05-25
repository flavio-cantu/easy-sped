package com.cantuaria.fiscal.nfe;

import com.cantuaria.fiscal.NotaFiscal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "IDO_IDE_ORIGINAL")
public class IDE {
    public static final String ID = "IDO_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IDO_DS_COD_UF")
    @JsonProperty("cUF")
    private String cUF;

    @Column(name = "IDO_DS_COD_NF")
    @JsonProperty("cNF")
    private String cNF;

    @Column(name = "IDO_DS_NAT_OP")
    @JsonProperty("natOp")
    private String natOp;

    @Column(name = "IDO_DS_MOD")
    @JsonProperty("mod")
    private String mod;

    @Column(name = "IDO_DS_SERIE")
    @JsonProperty("serie")
    private String serie;

    @Column(name = "IDO_DS_N_NF")
    @JsonProperty("nNF")
    private String nNF;

    @Column(name = "IDO_DS_DH_EMI")
    @JsonProperty("dhEmi")
    private String dhEmi;

    @Column(name = "IDO_DS_TP_NF")
    @JsonProperty("tpNF")
    private String tpNF;

    @Column(name = "IDO_DS_ID_DEST")
    @JsonProperty("idDest")
    private String idDest;

    @Column(name = "IDO_DS_COD_MUN_FG")
    @JsonProperty("cMunFG")
    private String cMunFG;

    @Column(name = "IDO_DS_TP_IMP")
    @JsonProperty("tpImp")
    private String tpImp;

    @Column(name = "IDO_DS_TP_EMIS")
    @JsonProperty("tpEmis")
    private String tpEmis;

    @Column(name = "IDO_DS_COD_DV")
    @JsonProperty("cDV")
    private String cDV;

    @Column(name = "IDO_DS_TP_AMB")
    @JsonProperty("tpAmb")
    private String tpAmb;

    @Column(name = "IDO_DS_FIN_NFE")
    @JsonProperty("finNFe")
    private String finNFe;

    @Column(name = "IDO_DS_IND_FINAL")
    @JsonProperty("indFinal")
    private String indFinal;

    @Column(name = "IDO_DS_IND_PRES")
    @JsonProperty("indPres")
    private String indPres;

    @Column(name = "IDO_DS_IND_INTERMED")
    @JsonProperty("indIntermed")
    private String indIntermed;

    @Column(name = "IDO_DS_PROC_EMI")
    @JsonProperty("procEmi")
    private String procEmi;

    @Column(name = "IDO_DS_VER_PROC")
    @JsonProperty("verProc")
    private String verProc;

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

    public String getcUF() {
        return cUF;
    }

    public void setcUF(String cUF) {
        this.cUF = cUF;
    }

    public String getcNF() {
        return cNF;
    }

    public void setcNF(String cNF) {
        this.cNF = cNF;
    }

    public String getNatOp() {
        return natOp;
    }

    public void setNatOp(String natOp) {
        this.natOp = natOp;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getnNF() {
        return nNF;
    }

    public void setnNF(String nNF) {
        this.nNF = nNF;
    }

    public String getDhEmi() {
        return dhEmi;
    }

    public void setDhEmi(String dhEmi) {
        this.dhEmi = dhEmi;
    }

    public String getTpNF() {
        return tpNF;
    }

    public void setTpNF(String tpNF) {
        this.tpNF = tpNF;
    }

    public String getIdDest() {
        return idDest;
    }

    public void setIdDest(String idDest) {
        this.idDest = idDest;
    }

    public String getcMunFG() {
        return cMunFG;
    }

    public void setcMunFG(String cMunFG) {
        this.cMunFG = cMunFG;
    }

    public String getTpImp() {
        return tpImp;
    }

    public void setTpImp(String tpImp) {
        this.tpImp = tpImp;
    }

    public String getTpEmis() {
        return tpEmis;
    }

    public void setTpEmis(String tpEmis) {
        this.tpEmis = tpEmis;
    }

    public String getcDV() {
        return cDV;
    }

    public void setcDV(String cDV) {
        this.cDV = cDV;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getFinNFe() {
        return finNFe;
    }

    public void setFinNFe(String finNFe) {
        this.finNFe = finNFe;
    }

    public String getIndFinal() {
        return indFinal;
    }

    public void setIndFinal(String indFinal) {
        this.indFinal = indFinal;
    }

    public String getIndPres() {
        return indPres;
    }

    public void setIndPres(String indPres) {
        this.indPres = indPres;
    }

    public String getIndIntermed() {
        return indIntermed;
    }

    public void setIndIntermed(String indIntermed) {
        this.indIntermed = indIntermed;
    }

    public String getProcEmi() {
        return procEmi;
    }

    public void setProcEmi(String procEmi) {
        this.procEmi = procEmi;
    }

    public String getVerProc() {
        return verProc;
    }

    public void setVerProc(String verProc) {
        this.verProc = verProc;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
}
