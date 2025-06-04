package com.cantuaria.sped.domain;

import com.cantuaria.util.ObjectNotFoundException;
import com.cantuaria.validation.EnumSped;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Domínio retirado do descriptor.xml
 * A=Perfil A;B=Perfil B;C=Perfil C
 */
@Getter
@AllArgsConstructor
public enum Profile implements EnumSped<String> {
    A("A", "Perfil A"),
    B("B", "Perfil A"),
    C("C", "Perfil A");

    private final String code;
    private final String description;

    public static Profile findByCode(String code){
        return Arrays.stream(Profile.values()).filter(enumerated -> enumerated.code.equals(code))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException("Perfil não encontrado"));
    }
}
