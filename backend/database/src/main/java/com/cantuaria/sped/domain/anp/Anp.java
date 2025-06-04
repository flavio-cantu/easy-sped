package com.cantuaria.sped.domain.anp;

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
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$ANP
 */

@Entity
@Table(name = "PAN_PRODUTO_ANP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anp {

    public static final String ID = "PAN_ID";

    @Id
    @Column(name = ID, length = 10)
    private String spedCode;

    @Column(name = "PAN_DS_GRUPO", length = 20, nullable = false)
    private String group;

    @Column(name = "PAN_DS_NOME", length = 100, nullable = false)
    private String name;
}
