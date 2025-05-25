package com.cantuaria.fiscal.nfe;

import com.cantuaria.fiscal.NotaFiscal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "IAO_INF_ADICIONAL_ORIGINAL")
public class InformacaoAdicional {
    public static final String ID = "IAO_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Frete
    private String modFrete;
    //Pag
    private String tPag;
    private Double vPag;
    //Responsável Tecnico
    @JsonProperty("CNPJ")
    private String cnpjResponsavelTecnico;
    @JsonProperty("xContato")
    private String nomeResponsavelTecnico;
    @JsonProperty("email")
    private String emailResponsavelTecnico;
    @JsonProperty("fone")
    private String telefone;

    private String qrCode;
    private String urlChave;

    //protNFe-infProt
    @JsonProperty("Id")
    private String idInfProt;
    private String tpAmb;
    private String verAplic;
    private String chNFe;
    private String dhRecbto;
    private String nProt;
    private String digVal;
    private String cStat;
    private String xMotivo;


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

    public String getModFrete() {
        return modFrete;
    }

    public void setModFrete(String modFrete) {
        this.modFrete = modFrete;
    }

    public String gettPag() {
        return tPag;
    }

    public void settPag(String tPag) {
        this.tPag = tPag;
    }

    public Double getvPag() {
        return vPag;
    }

    public void setvPag(Double vPag) {
        this.vPag = vPag;
    }

    public String getCnpjResponsavelTecnico() {
        return cnpjResponsavelTecnico;
    }

    public void setCnpjResponsavelTecnico(String cnpjResponsavelTecnico) {
        this.cnpjResponsavelTecnico = cnpjResponsavelTecnico;
    }

    public String getNomeResponsavelTecnico() {
        return nomeResponsavelTecnico;
    }

    public void setNomeResponsavelTecnico(String nomeResponsavelTecnico) {
        this.nomeResponsavelTecnico = nomeResponsavelTecnico;
    }

    public String getEmailResponsavelTecnico() {
        return emailResponsavelTecnico;
    }

    public void setEmailResponsavelTecnico(String emailResponsavelTecnico) {
        this.emailResponsavelTecnico = emailResponsavelTecnico;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getUrlChave() {
        return urlChave;
    }

    public void setUrlChave(String urlChave) {
        this.urlChave = urlChave;
    }

    public String getIdInfProt() {
        return idInfProt;
    }

    public void setIdInfProt(String idInfProt) {
        this.idInfProt = idInfProt;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getVerAplic() {
        return verAplic;
    }

    public void setVerAplic(String verAplic) {
        this.verAplic = verAplic;
    }

    public String getChNFe() {
        return chNFe;
    }

    public void setChNFe(String chNFe) {
        this.chNFe = chNFe;
    }

    public String getDhRecbto() {
        return dhRecbto;
    }

    public void setDhRecbto(String dhRecbto) {
        this.dhRecbto = dhRecbto;
    }

    public String getnProt() {
        return nProt;
    }

    public void setnProt(String nProt) {
        this.nProt = nProt;
    }

    public String getDigVal() {
        return digVal;
    }

    public void setDigVal(String digVal) {
        this.digVal = digVal;
    }

    public String getcStat() {
        return cStat;
    }

    public void setcStat(String cStat) {
        this.cStat = cStat;
    }

    public String getxMotivo() {
        return xMotivo;
    }

    public void setxMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
}
