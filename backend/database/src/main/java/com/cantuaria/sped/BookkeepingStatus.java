package com.cantuaria.sped;

import com.cantuaria.util.ObjectNotFoundException;
import com.cantuaria.validation.EnumSped;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Domínio do status da escrituração
 * Uma flag para que o usuário saiba do andamento da geração da escrituração
 */
@Getter
@AllArgsConstructor
public enum BookkeepingStatus implements EnumSped<String> {

    PENDING("P", "Pendente"),
    CONSOLIDATED("C", "Consolidado");

    private final String code;
    private final String description;

    public static BookkeepingStatus findByCode(String code) {
        return Arrays.stream(BookkeepingStatus.values()).filter(status -> status.code.equals(code))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException("Status da escrituração não encontrado"));
    }

    public static BookkeepingStatus findByDescription(String description) {
        return Arrays.stream(BookkeepingStatus.values()).filter(status -> status.description.equals(description))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException("Status da escrituração não encontrado"));
    }

}
