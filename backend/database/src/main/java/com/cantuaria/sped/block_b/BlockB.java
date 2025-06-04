package com.cantuaria.sped.block_b;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do bloco B do arquivo de escrituração
 * <p>
 * Basicamente o bloco B trata das informações das notas fiscais
 */
@Entity
@Table(name = "EBB_ESCRITURACAO_BLOCO_B")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockB {

    public static final String ID = "EBB_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @SpedInnerObject(required = true)
    private RecordB001 recordB001;

    @Transient
    @SpedInnerObject(required = true)
    private RecordB990 recordB990;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}