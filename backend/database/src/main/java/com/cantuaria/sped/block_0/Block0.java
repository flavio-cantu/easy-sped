package com.cantuaria.sped.block_0;

import com.cantuaria.sped.Bookkeeping;
import jakarta.persistence.*;

/**
 * Representação do bloco 0 do arquivo de escrituração
 * <p>
 * Basicamente o bloco 0 trata das informações das notas fiscais
 */
@Entity
@Table(name = "EB0_ESCRITURACAO_BLOCO_0")
public class Block0 {

    public static final String ID = "EB0_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Record0000 record0000;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}