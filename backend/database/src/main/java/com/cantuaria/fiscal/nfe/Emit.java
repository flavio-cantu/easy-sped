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
@Table(name = "EMO_EMIT_ORIGINAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
