package com.cantuaria.sped.block_b;

import com.cantuaria.sped.Bookkeeping;
import jakarta.persistence.*;

/**
 * Representação do bloco B do arquivo de escrituração
 * <p>
 * Basicamente o bloco B trata das informações das notas fiscais
 */
@Entity
@Table(name = "EBB_ESCRITURACAO_BLOCO_B")
public class BlockB {

    public static final String ID = "EBB_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}