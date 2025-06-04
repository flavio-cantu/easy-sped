package com.cantuaria.sped.block_g;

import com.cantuaria.sped.EndingBlock;
import com.cantuaria.validation.SpedValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do registro G990 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@SpedValidation(validation = {
        "REGRA_QTD_LIN_BLOCOG",
        "REGRA_BLOCO_COM_SEM_MOVIMENTO"
}, label = "ENCERRAMENTO DO BLOCO G", description = "ENCERRAMENTO DO BLOCO G")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RecordG990 extends EndingBlock {
    public static final String REG = "G990";

}