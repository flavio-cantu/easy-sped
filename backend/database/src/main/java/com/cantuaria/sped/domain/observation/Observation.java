package com.cantuaria.sped.domain.observation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Observação do lançamento fiscal
 * Informação retirada do item 0460 do guia:
 * Este registro é utilizado para informar anotações de escrituração determinadas pela legislação pertinente aos
 * lançamentos fiscais, tais como: ajustes efetuados por diferimento parcial de imposto, antecipações, diferencial de alíquota e
 * outros.
 *
 */

@Entity
@Table(name = "OLF_OBSERVACAO_LANCAMENTO_FISCAL")
public class Observation {

    public static final String ID = "OLF_ID";

    @Id
    @Column(name = ID, length = 6)
    private String code;

    @Column(name = "OLF_DS_NOME", length = 20, nullable = false)
    private String name;

    @Column(name = "OLF_DS", length = 600, nullable = false)
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
