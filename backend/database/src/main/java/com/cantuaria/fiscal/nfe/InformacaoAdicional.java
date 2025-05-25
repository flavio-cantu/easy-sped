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
    @Column(name = "IAO_DS_MOD_FRETE")
    @JsonProperty("modFrete")
    private String modFrete;
    
    //Pag
    @Column(name = "IAO_DS_T_PAG")
    @JsonProperty("tPag")
    private String tPag;

    @Column(name = "IAO_VL_V_PAG")
    @JsonProperty("vPag")
    private Double vPag;
    
    //Responsável Tecnico
    @Column(name = "IAO_DS_CNPJ_RESP_TECNICO")
    @JsonProperty("CNPJ")
    private String cnpjResponsavelTecnico;

    @Column(name = "IAO_DS_CONTATO_RESP_TECNICO")
    @JsonProperty("xContato")
    private String nomeResponsavelTecnico;

    @Column(name = "IAO_DS_EMAIL_RESP_TECNICO")
    @JsonProperty("email")
    private String emailResponsavelTecnico;

    @Column(name = "IAO_DS_TELEFONE_RESP_TECNICO")
    @JsonProperty("fone")
    private String phone;


    @Column(name = "IAO_DS_QR_CODE")
    @JsonProperty("qrCode")
    private String qrCode;

    @Column(name = "IAO_DS_URL_CHAVE")
    @JsonProperty("urlChave")
    private String urlChave;

    //protNFe-infProt
    @Column(name = "IAO_DS_ID_IND_PROFT")
    @JsonProperty("Id")
    private String idInfProt;

    @Column(name = "IAO_DS_TP_AMB")
    @JsonProperty("tpAmb")
    private String tpAmb;

    @Column(name = "IAO_DS_VER_APLIC")
    @JsonProperty("verAplic")
    private String verAplic;

    @Column(name = "IAO_DS_CH_NFE")
    @JsonProperty("chNFe")
    private String chNFe;

    @Column(name = "IAO_DS_DH_RECEBIMENTO")
    @JsonProperty("dhRecbto")
    private String dhRecbto;

    @Column(name = "IAO_DS_N_PROT")
    @JsonProperty("nProt")
    private String nProt;

    @Column(name = "IAO_DS_DIG_VAL")
    @JsonProperty("digVal")
    private String digVal;

    @Column(name = "IAO_DS_C_STAT")
    @JsonProperty("cStat")
    private String cStat;

    @Column(name = "IAO_DS_MOTIVO")
    @JsonProperty("xMotivo")
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
