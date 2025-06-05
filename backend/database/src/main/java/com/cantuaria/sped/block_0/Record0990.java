package com.cantuaria.sped.block_0;

import com.cantuaria.sped.EndingBlock;
import com.cantuaria.validation.SpedValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Representação do registro 0990 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@SpedValidation(validation = {
        "REGRA_QTD_LIN_BLOCO0",
        "REGRA_BLOCO_COM_SEM_MOVIMENTO"
}, label = "ENCERRAMENTO DO BLOCO 0", description = "ENCERRAMENTO DO BLOCO 0")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Record0990 extends EndingBlock {
    public static final String REG = "0990";
}