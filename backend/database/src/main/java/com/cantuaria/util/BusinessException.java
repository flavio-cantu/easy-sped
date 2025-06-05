package com.cantuaria.util;

import com.cantuaria.validation.ValidationMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

//TODO criar um handler pra isso no web
@AllArgsConstructor
@Getter
public class BusinessException extends RuntimeException {
    private List<ValidationMessage> validations;
}
