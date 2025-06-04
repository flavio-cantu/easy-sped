package com.cantuaria.sped.domain;

import com.cantuaria.validation.EnumSped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ActivityType implements EnumSped<Integer> {

    INDUSTRIAL(0, "Industrial ou equiparado a industrial"),
    OUTRAS(1, "Outras");

    private final Integer code;
    private final String name;

}
