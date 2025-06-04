package com.cantuaria.sped.domain.classification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Classificação do Contribuinte conforme tabela 4.5.5
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_IPI$CLASS_CONTRIB_IPI
 */

@Entity
@Table(name = "CLC_CLASSIFICACAO_CONTRIBUINTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classification {

    public static final String ID = "CLC_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CLC_CD_ATIVIDADE", length = 2, nullable = false)
    private String spedCode;

    @Column(name = "CLC_DS_ATIVIDADE", length = 70, nullable = false)
    private String description;
}
