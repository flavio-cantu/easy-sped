package com.cantuaria.sped.block_9;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do registro 9001 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record9001 {
    public static final String REG = "9001";
    private boolean block9HasInfo;
}