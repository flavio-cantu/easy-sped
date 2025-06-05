package com.cantuaria.sped.block_e;

import com.cantuaria.sped.EndingBlock;
import com.cantuaria.validation.SpedValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Representação do registro E990 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@SpedValidation(validation = {
        "REGRA_QTD_LIN_BLOCOE",
        "REGRA_BLOCO_COM_SEM_MOVIMENTO"
}, label = "ENCERRAMENTO DO BLOCO E", description = "ENCERRAMENTO DO BLOCO E")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RecordE990 extends EndingBlock {
    public static final String REG = "E990";

}