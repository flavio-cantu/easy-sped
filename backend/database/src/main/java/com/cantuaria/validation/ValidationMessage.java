package com.cantuaria.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ValidationMessage {

    private String message;

}
