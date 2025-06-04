package com.cantuaria.sped.block_e;

import com.cantuaria.validation.SpedValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do registro E001 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@SpedValidation(validation = "REGRA_OBRIGATORIO_E200_17001_V1",
        label = "ABERTURA DO BLOCO E", description = "ABERTURA DO BLOCO E")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordE001 {
    public static final String REG = "E001";
    private boolean blockEHasInfo;
}