package com.cantuaria.sped.block_0;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do registro 0001 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0001 {
    public static final String REG = "0001";
    private boolean block0HasInfo;
}