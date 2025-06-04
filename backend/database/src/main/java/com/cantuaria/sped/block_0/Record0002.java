package com.cantuaria.sped.block_0;

import com.cantuaria.sped.domain.ActivityType;
import com.cantuaria.sped.domain.Profile;
import com.cantuaria.sped.domain.Purpose;
import com.cantuaria.sped.domain.UF;
import com.cantuaria.sped.domain.classification.ClassificationRepository;
import com.cantuaria.sped.domain.layout_version.LayoutVersionRepository;
import com.cantuaria.sped.domain.municipio.MunicipalityRepository;
import com.cantuaria.validation.SpedDatabaseValidation;
import com.cantuaria.validation.SpedEnumValidation;
import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do registro 0002 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0002")
@SpedValidation(validation = "REGRA_REGISTRO_OBRIGATORIO_0002", label = "Complemento", description = "Dados Complementares")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0002 {

    public static final String REG = "0002";
    public static final String ID = "B0R0002_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Client.classification
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = ClassificationRepository.class,
            label = "Classificação do Contribuinte", description = "Classificação do Contribuinte conforme tabela 4.5.5.")
    @Column(name = "CLAS_ESTAB_IND", length = 2, nullable = false)
    private String clasEstabInd;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}