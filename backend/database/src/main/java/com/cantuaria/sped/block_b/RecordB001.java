package com.cantuaria.sped.block_b;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do registro B001 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordB001 {
    public static final String REG = "B001";
    private boolean blockBHasInfo;
}