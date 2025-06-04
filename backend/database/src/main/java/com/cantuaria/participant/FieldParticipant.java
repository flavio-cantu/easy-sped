package com.cantuaria.participant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum FieldParticipant {

    NAME("03"),
    COUNTRY("04"),
    CNPJ("05"),
    CPF("06"),
    MUNICIPALITY("08"),
    SUFRAMA("09"),
    ADDRESS("10"),
    NUMBER("11"),
    COMPLEMENT("12"),
    NEIGHBORHOOD("13");

    private final String fieldCode;
}
