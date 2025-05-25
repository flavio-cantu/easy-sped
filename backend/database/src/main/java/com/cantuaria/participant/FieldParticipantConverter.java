package com.cantuaria.participant;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class FieldParticipantConverter implements AttributeConverter<FieldParticipant, String> {
    @Override
    public String convertToDatabaseColumn(FieldParticipant enumerated) {
        return enumerated.getFieldCode();
    }

    @Override
    public FieldParticipant convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(FieldParticipant.values())
                .filter(c -> c.getFieldCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
