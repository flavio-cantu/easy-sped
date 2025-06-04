package com.cantuaria.sped.domain.gender;

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
 * SPED/recursos/TabelasExternas/SPEDFISCAL_GLOBAL$GEN_ITEM_MERC_SERV
 */

@Entity
@Table(name = "GEI_GENERO_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gender {

    public static final String ID = "GEI_ID";

    @Id
    @Column(name = ID, length = 2)
    private String spedCode;

    @Column(name = "GEI_DS_NOME", length = 300, nullable = false)
    private String name;
}
