package com.cantuaria.sped.block_g;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do bloco G do arquivo de escrituração
 */
@Entity
@Table(name = "EBG_ESCRITURACAO_BLOCO_G")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockG {

    public static final String ID = "EBG_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @SpedInnerObject(required = true)
    private RecordG001 recordG001;

    @Transient
    @SpedInnerObject(required = true)
    private RecordG990 recordG990;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}