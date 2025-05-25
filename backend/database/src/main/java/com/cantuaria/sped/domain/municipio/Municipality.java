package com.cantuaria.sped.domain.municipio;

import jakarta.persistence.*;

/**
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$MUNICIPIOS
 */

@Entity
@Table(name = "MUN_MUNICIPIO")
public class Municipality {

    public static final String ID = "MUN_ID";

    @Id
    @Column(name = ID, length = 7)
    private String spedCode;

    @Column(name = "MUN_DS_NOME", length = 50, nullable = false)
    private String name;

    public String getSpedCode() {
        return spedCode;
    }

    public void setSpedCode(String spedCode) {
        this.spedCode = spedCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
