package com.cantuaria.sped.block_k;

import com.cantuaria.sped.EndingBlock;
import com.cantuaria.validation.SpedValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Representação do registro K990 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@SpedValidation(validation = {
        "REGRA_QTD_LIN_BLOCOK",
        "REGRA_BLOCO_COM_SEM_MOVIMENTO"
}, label = "ENCERRAMENTO DO BLOCO K", description = "ENCERRAMENTO DO BLOCO K")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RecordK990 extends EndingBlock {
    public static final String REG = "K990";

}