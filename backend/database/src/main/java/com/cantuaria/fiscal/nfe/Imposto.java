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
@Table(name = "IMO_IMPOSTO_ORIGINAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Imposto {
    public static final String ID = "IMO_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "IMO_DS_ICMS_ORIG")
    @JsonProperty("icmsOrig")
    private String icmsOrig;

    @Column(name = "IMO_DS_ICMS_CST")
    @JsonProperty("icmsCST")
    private String icmsCST;

    @Column(name = "IMO_VL_ICMS_BC_MONO_RET")
    @JsonProperty("icmsqBCMonoRet")
    private Double icmsqBCMonoRet;

    @Column(name = "IMO_VL_ICMS_AD_REM_RET")
    @JsonProperty("icmsadRemICMSRet")
    private Double icmsadRemICMSRet;

    @Column(name = "IMO_VL_ICMS_V_MONO_RET")
    @JsonProperty("icmsvICMSMonoRet")
    private Double icmsvICMSMonoRet;


    @Column(name = "IMO_DS_PIS_CST")
    @JsonProperty("pisCST")
    private String pisCST;

    @Column(name = "IMO_DS_COFINS_CST")
    @JsonProperty("confinsCST")
    private String confinsCST;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Det.ID, nullable = false)
    @JsonIgnore
    private Det det;

}
