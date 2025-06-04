package com.cantuaria.sped.domain;

import com.cantuaria.util.ObjectNotFoundException;
import com.cantuaria.validation.EnumSped;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Domínio retirado do descriptor.xml
 * 0=Remessa do arquivo original;1=Remessa do arquivo substituto
 */
@Getter
@AllArgsConstructor
public enum Purpose implements EnumSped<Integer> {

    ORIGINAL(0, "Remessa do arquivo original"),
    SUBSTITUTE(1, "Remessa do arquivo substituto");

    private final Integer code;
    private final String description;

    public static Purpose findByCode(Integer code){
        return Arrays.stream(Purpose.values()).filter(purpose -> purpose.code == code)
                .findFirst().orElseThrow(() -> new ObjectNotFoundException("Propósito não encontrado"));
    }

}
