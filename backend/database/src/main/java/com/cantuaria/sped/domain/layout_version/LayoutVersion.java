package com.cantuaria.sped.domain.layout_version;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$VERSOES_LEIAUTE
 */

@Entity
@Table(name = "LAV_LAYOUT_VERSION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
