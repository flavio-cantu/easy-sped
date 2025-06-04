package com.cantuaria.sped.block_9;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do bloco 9 do arquivo de escrituração
 */
@Entity
@Table(name = "EB9_ESCRITURACAO_BLOCO_9")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Block9 {

    public static final String ID = "EB9_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @SpedInnerObject(required = true)
    private Record9001 record9001;

    @Transient
    @SpedInnerObject(required = true)
    private Record9990 record9990;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}