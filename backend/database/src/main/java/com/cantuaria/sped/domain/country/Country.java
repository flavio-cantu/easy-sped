package com.cantuaria.sped.domain.country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$PAISES
 */

@Entity
@Table(name = "PAI_PAIS")
public class Country {

    public static final String ID = "PAI_ID";

    @Id
    @Column(name = ID, length = 5)
    private String code;

    @Column(name = "PAI_DS_NOME", length = 50, nullable = false)
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
