package com.cantuaria.sped.block_1;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do bloco 1 do arquivo de escrituração
 */
@Entity
@Table(name = "EB1_ESCRITURACAO_BLOCO_1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Block1 {

    public static final String ID = "EB1_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @SpedInnerObject(required = true)
    private Record1001 record1001;

    @Transient
    @SpedInnerObject(required = true)
    private Record1990 record1990;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}