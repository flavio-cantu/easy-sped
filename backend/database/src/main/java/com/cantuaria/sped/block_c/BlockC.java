package com.cantuaria.sped.block_c;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do bloco C do arquivo de escrituração
 * <p>
 * Basicamente o bloco C trata das informações das notas fiscais
 */
@Entity
@Table(name = "EBC_ESCRITURACAO_BLOCO_C")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockC {

    public static final String ID = "EBC_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @SpedInnerObject(required = true)
    private RecordC001 recordC001;

    @Transient
    @SpedInnerObject(required = true)
    private RecordC990 recordC990;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}