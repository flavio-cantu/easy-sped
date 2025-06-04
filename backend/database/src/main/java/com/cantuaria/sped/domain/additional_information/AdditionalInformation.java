package com.cantuaria.sped.domain.additional_information;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Informação Complementar
 * Informação retirada do item 0450 do guia:
 * Este registro tem por objetivo codificar todas as informações complementares dos documentos fiscais exigidas pela
 * legislação fiscal. Estas informações constam no campo “Dados Adicionais” dos documentos fiscais
 * <p>
 * TODO entidade sem vinculo, mas utiliado no C170
 */

@Entity
@Table(name = "INC_INFORMACAO_COMPLEMENTAR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdditionalInformation {

    public static final String ID = "INC_ID";

    @Id
    @Column(name = ID, length = 6)
    private String code;

    @Column(name = "INC_DS_NOME", length = 20, nullable = false)
    private String name;

    @Column(name = "INC_DS", length = 600, nullable = false)
    private String description;
}
