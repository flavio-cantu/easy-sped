package com.cantuaria.sped.block_9;

import com.cantuaria.sped.EndingBlock;
import com.cantuaria.validation.SpedValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Representação do registro 9990 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@SpedValidation(validation = {
        "REGRA_QTD_LIN_BLOCO9",
        "REGRA_BLOCO_COM_SEM_MOVIMENTO"
}, label = "ENCERRAMENTO DO BLOCO 9", description = "ENCERRAMENTO DO BLOCO 9")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Record9990 extends EndingBlock {
    public static final String REG = "9990";
}