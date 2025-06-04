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
@Table(name = "IAO_INF_ADICIONAL_ORIGINAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
