package com.cantuaria.sped.domain.layout_version;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$VERSOES_LEIAUTE
 */

@Entity
@Table(name = "LAV_LAYOUT_VERSION")
public class LayoutVersion {
    public static final String ID = "LAV_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LAV_COD_LEI", length = 3, nullable = false)
    private String spedCode;

    @Column(name = "LAV_VERSION", length = 4, nullable = false)
    private String version;

    @Column(name = "LAV_DT_INICIO", nullable = false)
    private LocalDate start;

    @Column(name = "LAV_DT_FIM")
    private LocalDate end;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpedCode() {
        return spedCode;
    }

    public void setSpedCode(String spedCode) {
        this.spedCode = spedCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
