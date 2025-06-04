package com.cantuaria.sped.block_d;

import com.cantuaria.sped.EndingBlock;
import com.cantuaria.validation.SpedValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Representação do registro D990 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@SpedValidation(validation = {
        "REGRA_QTD_LIN_BLOCOD",
        "REGRA_BLOCO_COM_SEM_MOVIMENTO"
}, label = "ENCERRAMENTO DO BLOCO D", description = "ENCERRAMENTO DO BLOCO D")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RecordD990 extends EndingBlock {
    public static final String REG = "D990";

}