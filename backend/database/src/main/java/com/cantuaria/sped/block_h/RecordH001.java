package com.cantuaria.sped.block_h;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do registro H001 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordH001 {
    public static final String REG = "H001";
    private boolean blockHHasInfo;
}