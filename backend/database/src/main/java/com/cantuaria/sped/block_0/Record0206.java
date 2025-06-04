package com.cantuaria.sped.block_0;

import com.cantuaria.sped.domain.anp.AnpRepository;
import com.cantuaria.validation.SpedDatabaseValidation;
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
 * Representação do registro 0206 do arquivo de escrituração
 *
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0206")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0206 {

    public static final String REG = "0206";
    public static final String ID = "B0R0206_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Item.anp
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = AnpRepository.class,
            label = "Código do produto, conforme tabela ANP", description = "Código do produto, conforme tabela ANP")
    @Column(name = "COD_COMB", length = 10, nullable = false)
    private String codComb;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Record0200.ID, nullable = false)
    private Record0200 record0200;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}