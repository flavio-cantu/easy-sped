package com.cantuaria.sped.domain;

import com.cantuaria.validation.EnumSped;

public enum ActivityType implements EnumSped<Integer> {

    INDUSTRIAL(0,"Industrial ou equiparado a industrial"),
    OUTRAS(1,"Outras");

    private Integer code;
    private String name;

    ActivityType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
