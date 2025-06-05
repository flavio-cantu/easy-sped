package com.cantuaria.validation;

import com.cantuaria.util.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class BusinessValidation {
    private final List<ValidationMessage> validations = new ArrayList<>();

    public void checkRule(boolean result, ValidationMessage validationMessage) {
        if (result) {
            validations.add(validationMessage);
        }
    }

    public void checkRule(boolean result, String simpleMessage) {
        checkRule(result, new ValidationMessage(simpleMessage));
    }

    public void throwIfNotEmpty() {
        if (!validations.isEmpty()) {
            throw new BusinessException(validations);
        }
    }
}
