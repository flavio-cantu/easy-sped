package com.cantuaria.sped.domain.converter;

import com.cantuaria.sped.domain.ActivityType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ActivityTypeConverter implements AttributeConverter<ActivityType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ActivityType purpose) {
        return purpose.getCode();
    }

    @Override
    public ActivityType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(ActivityType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
