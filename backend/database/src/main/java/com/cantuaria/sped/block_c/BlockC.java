package com.cantuaria.sped.block_c;

import com.cantuaria.sped.Bookkeeping;
import jakarta.persistence.*;

/**
 * Representação do bloco C do arquivo de escrituração
 * <p>
 * Basicamente o bloco C trata das informações das notas fiscais
 */
@Entity
@Table(name = "EBC_ESCRITURACAO_BLOCO_C")
public class BlockC {

    public static final String ID = "EBC_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}