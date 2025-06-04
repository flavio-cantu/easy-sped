package com.cantuaria.sped.domain.municipio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$MUNICIPIOS
 */

@Entity
@Table(name = "MUN_MUNICIPIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Municipality {

    public static final String ID = "MUN_ID";

    @Id
    @Column(name = ID, length = 7)
    private String spedCode;

    @Column(name = "MUN_DS_NOME", length = 50, nullable = false)
    private String name;
}
