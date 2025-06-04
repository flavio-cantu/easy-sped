package com.cantuaria.sped.block_k;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do bloco K do arquivo de escrituração
 */
@Entity
@Table(name = "EBK_ESCRITURACAO_BLOCO_K")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockK {

    public static final String ID = "EBK_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @SpedInnerObject(required = true)
    private RecordK001 recordK001;

    @Transient
    @SpedInnerObject(required = true)
    private RecordK990 recordK990;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}