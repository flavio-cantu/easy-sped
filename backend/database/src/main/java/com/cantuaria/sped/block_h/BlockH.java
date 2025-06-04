package com.cantuaria.sped.block_h;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do bloco H do arquivo de escrituração
 */
@Entity
@Table(name = "EBH_ESCRITURACAO_BLOCO_H")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockH {

    public static final String ID = "EBH_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @SpedInnerObject(required = true)
    private RecordH001 recordH001;

    @Transient
    @SpedInnerObject(required = true)
    private RecordH990 recordH990;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}