package com.cantuaria.sped.domain.classification;

import jakarta.persistence.*;

import java.time.LocalDate;

/** Classificação do Contribuinte conforme tabela 4.5.5
 * Domínio retirado da classe:
 * SPED/recursos/TabelasExternas/SPEDFISCAL_IPI$CLASS_CONTRIB_IPI
 */

@Entity
@Table(name = "CLC_CLASSIFICACAO_CONTRIBUINTE")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpedCode() {
        return spedCode;
    }

    public void setSpedCode(String spedCode) {
        this.spedCode = spedCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
