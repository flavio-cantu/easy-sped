package com.cantuaria.sped.domain.converter;

import com.cantuaria.sped.domain.Purpose;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PurposeConverter implements AttributeConverter<Purpose, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Purpose enumerate) {
        if (enumerate == null) {
            return null;
        }
        return enumerate.getCode();
    }

    @Override
    public Purpose convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Purpose.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
