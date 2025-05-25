package com.cantuaria.fiscal.nfe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "IMO_IMPOSTO_ORIGINAL")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcmsOrig() {
        return icmsOrig;
    }

    public void setIcmsOrig(String icmsOrig) {
        this.icmsOrig = icmsOrig;
    }

    public String getIcmsCST() {
        return icmsCST;
    }

    public void setIcmsCST(String icmsCST) {
        this.icmsCST = icmsCST;
    }

    public Double getIcmsqBCMonoRet() {
        return icmsqBCMonoRet;
    }

    public void setIcmsqBCMonoRet(Double icmsqBCMonoRet) {
        this.icmsqBCMonoRet = icmsqBCMonoRet;
    }

    public Double getIcmsadRemICMSRet() {
        return icmsadRemICMSRet;
    }

    public void setIcmsadRemICMSRet(Double icmsadRemICMSRet) {
        this.icmsadRemICMSRet = icmsadRemICMSRet;
    }

    public Double getIcmsvICMSMonoRet() {
        return icmsvICMSMonoRet;
    }

    public void setIcmsvICMSMonoRet(Double icmsvICMSMonoRet) {
        this.icmsvICMSMonoRet = icmsvICMSMonoRet;
    }

    public String getPisCST() {
        return pisCST;
    }

    public void setPisCST(String pisCST) {
        this.pisCST = pisCST;
    }

    public String getConfinsCST() {
        return confinsCST;
    }

    public void setConfinsCST(String confinsCST) {
        this.confinsCST = confinsCST;
    }

    public Det getDet() {
        return det;
    }

    public void setDet(Det det) {
        this.det = det;
    }
}
