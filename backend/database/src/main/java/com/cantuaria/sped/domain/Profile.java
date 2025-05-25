package com.cantuaria.sped.domain;

import com.cantuaria.validation.EnumSped;

/**
 * Domínio retirado do descriptor.xml
 * A=Perfil A;B=Perfil B;C=Perfil C
 */
public enum Profile implements EnumSped<String> {
    A("A", "Perfil A"),
    B("B", "Perfil A"),
    C("C", "Perfil A");

    private String code;
    private String description;

    Profile(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
