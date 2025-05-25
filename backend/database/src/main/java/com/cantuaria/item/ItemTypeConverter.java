package com.cantuaria.item;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ItemTypeConverter implements AttributeConverter<ItemType, String> {
    @Override
    public String convertToDatabaseColumn(ItemType enumerated) {
        return enumerated.getCode();
    }

    @Override
    public ItemType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(ItemType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
