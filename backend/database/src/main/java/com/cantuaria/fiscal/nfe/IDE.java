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
@Table(name = "IDO_IDE_ORIGINAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
