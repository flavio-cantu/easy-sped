package com.cantuaria.sped.block_h;

import com.cantuaria.sped.EndingBlock;
import com.cantuaria.validation.SpedValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Representação do registro H990 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@SpedValidation(validation = {
        "REGRA_QTD_LIN_BLOCOH",
        "REGRA_BLOCO_COM_SEM_MOVIMENTO"
}, label = "ENCERRAMENTO DO BLOCO H", description = "ENCERRAMENTO DO BLOCO H")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RecordH990 extends EndingBlock {
    public static final String REG = "H990";

}