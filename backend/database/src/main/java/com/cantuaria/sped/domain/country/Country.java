package com.cantuaria.sped.domain.country;

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
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$PAISES
 */

@Entity
@Table(name = "PAI_PAIS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    public static final String ID = "PAI_ID";

    @Id
    @Column(name = ID, length = 5)
    private String spedCode;

    @Column(name = "PAI_DS_NOME", length = 50, nullable = false)
    private String name;
}
