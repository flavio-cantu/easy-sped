package com.cantuaria.fiscal.nfe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "COM_COMB_ORIGINAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
