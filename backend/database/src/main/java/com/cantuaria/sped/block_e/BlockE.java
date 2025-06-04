package com.cantuaria.sped.block_e;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do bloco E do arquivo de escrituração
 */
@Entity
@Table(name = "EBE_ESCRITURACAO_BLOCO_E")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockE {

    public static final String ID = "EBE_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @SpedInnerObject(required = true)
    private RecordE001 recordE001;

    @Transient
    @SpedInnerObject(required = true)
    private RecordE990 recordE990;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}