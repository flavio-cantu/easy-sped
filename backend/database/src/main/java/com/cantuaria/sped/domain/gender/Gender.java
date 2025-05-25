package com.cantuaria.sped.domain.gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$GEN_ITEM_MERC_SERV
 */

@Entity
@Table(name = "GEI_GENERO_ITEM")
public class Gender {

    public static final String ID = "GEI_ID";

    @Id
    @Column(name = ID, length = 2)
    private String code;

    @Column(name = "GEI_DS_NOME", length = 300, nullable = false)
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
