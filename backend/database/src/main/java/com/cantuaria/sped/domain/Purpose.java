package com.cantuaria.sped.domain;

import com.cantuaria.validation.EnumSped;

/**
 * Domínio retirado do descriptor.xml
 * 0=Remessa do arquivo original;1=Remessa do arquivo substituto
 */
public enum Purpose implements EnumSped<Integer> {

    ORIGINAL(0, "Remessa do arquivo original"),
    SUBSTITUTE(1, "Remessa do arquivo substituto"),;

    private Integer code;
    private String description;

    Purpose(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
