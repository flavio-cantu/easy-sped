package com.cantuaria.sped.domain.cest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Abreviado de: Código Especificador de Substituição Tribuitária
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$COD_CEST
 */

@Entity
@Table(name = "SUT_SUBSTITUICAO_TRIBUTARIA")
public class Cest {

    public static final String ID = "SUT_ID";

    @Id
    @Column(name = ID, length = 7)
    private String code;

    @Column(name = "SUT_DS_NOME", length = 350, nullable = false)
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
