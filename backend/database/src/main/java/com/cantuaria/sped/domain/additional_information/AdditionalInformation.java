package com.cantuaria.sped.domain.additional_information;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Informação Complementar
 * Informação retirada do item 0450 do guia:
 * Este registro tem por objetivo codificar todas as informações complementares dos documentos fiscais exigidas pela
 * legislação fiscal. Estas informações constam no campo “Dados Adicionais” dos documentos fiscais
 *
 * TODO entidade sem vinculo, mas utiliado no C170
 */

@Entity
@Table(name = "INC_INFORMACAO_COMPLEMENTAR")
public class AdditionalInformation {

    public static final String ID = "INC_ID";

    @Id
    @Column(name = ID, length = 6)
    private String code;

    @Column(name = "INC_DS_NOME", length = 20, nullable = false)
    private String name;

    @Column(name = "INC_DS", length = 600, nullable = false)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
