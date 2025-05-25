package com.cantuaria.participant;

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

    private String fieldCode;

    FieldParticipant(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldCode() {
        return fieldCode;
    }
}
