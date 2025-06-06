package com.cantuaria.sped.domain;

import com.cantuaria.validation.EnumSped;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UF implements EnumSped<String> {

    AC("AC", "Acre"),
    AL("AL", "Alagoas"),
    AM("AM", "Amazonas"),
    AP("AP", "Amapá"),
    BA("BA", "Bahia"),
    DF("DF", "Distrito Federal"),
    CE("CE", "Ceará"),
    ES("ES", "Espírito Santo"),
    GO("GO", "Goiás"),
    MA("MA", "Maranhão"),
    MT("MT", "Mato Grosso"),
    MS("MS", "Mato Grosso do Sul"),
    MG("MG", "Minas Gerais"),
    PA("PA", "Pará"),
    PB("PB", "Paraíba"),
    PE("PE", "Pernambuco"),
    PR("PR", "Paraná"),
    PI("PI", "Piauí"),
    RJ("RJ", "Rio de Janeiro"),
    RN("RN", "Rio Grande do Norte"),
    RS("RS", "Rio Grande do Sul"),
    RR("RR", "Roraima"),
    RO("RO", "Rondônia"),
    SC("SC", "Santa Catarina"),
    SP("SP", "São Paulo"),
    SE("SE", "Sergipe"),
    TO("TO", "Tocantins");

    private final String uf;
    private final String name;


    @Override
    public String getCode() {
        return getUf();
    }
}
