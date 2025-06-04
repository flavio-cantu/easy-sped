package com.cantuaria.sped.block_d;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do bloco D do arquivo de escrituração
 */
@Entity
@Table(name = "EBD_ESCRITURACAO_BLOCO_D")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockD {

    public static final String ID = "EBD_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @SpedInnerObject(required = true)
    private RecordD001 recordD001;

    @Transient
    @SpedInnerObject(required = true)
    private RecordD990 recordD990;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}