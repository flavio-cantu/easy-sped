package com.cantuaria.sped.domain.converter;

import com.cantuaria.sped.BookkeepingStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BookkeepingStatusConverter implements AttributeConverter<BookkeepingStatus, String> {
    @Override
    public String convertToDatabaseColumn(BookkeepingStatus enumerate) {
        if (enumerate == null) {
            return null;
        }
        return enumerate.getCode();
    }

    @Override
    public BookkeepingStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return BookkeepingStatus.findByCode(code);
    }
}
