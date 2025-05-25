package com.cantuaria.sped.domain.anp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Abreviado de: Código Especificador de Substituição Tribuitária
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$ANP
 */

@Entity
@Table(name = "PAN_PRODUTO_ANP")
public class Anp {

    public static final String ID = "PAN_ID";

    @Id
    @Column(name = ID, length = 10)
    private String code;

    @Column(name = "PAN_DS_GRUPO", length = 20, nullable = false)
    private String group;

    @Column(name = "PAN_DS_NOME", length = 100, nullable = false)
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
