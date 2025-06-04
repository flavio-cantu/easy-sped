package com.cantuaria.sped.domain.cest;

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
 * Abreviado de: Código Especificador de Substituição Tribuitária
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$COD_CEST
 */

@Entity
@Table(name = "SUT_SUBSTITUICAO_TRIBUTARIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cest {

    public static final String ID = "SUT_ID";

    @Id
    @Column(name = ID, length = 7)
    private String spedCode;

    @Column(name = "SUT_DS_NOME", length = 350, nullable = false)
    private String name;
}
