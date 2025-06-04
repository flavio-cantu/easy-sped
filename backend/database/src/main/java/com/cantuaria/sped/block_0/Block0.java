package com.cantuaria.sped.block_0;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.SpedInnerObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Representação do bloco 0 do arquivo de escrituração
 * <p>
 * Basicamente o bloco 0 trata das informações das notas fiscais
 */
@Entity
@Table(name = "EB0_ESCRITURACAO_BLOCO_0")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Block0 {

    public static final String ID = "EB0_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject(required = true)
    private Record0000 record0000;

    @Transient
    @SpedInnerObject(required = true)
    private Record0001 record0001;

    @OneToOne(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //Obrigatório na regra REGRA_REGISTRO_OBRIGATORIO_0002
    private Record0002 record0002;

    @OneToOne(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject(required = true)
    private Record0005 record0005;

    @OneToOne(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject
    //Pode ser obrigatório por regra interna
    private Record0100 record0100;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject
    //Pode ser obrigatório por regra interna
    private List<Record0150> records0150;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject
    //Pode ser obrigatório por regra interna
    private List<Record0190> records0190;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject
    //Pode ser obrigatório por regra interna
    private List<Record0200> records0200;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject
    //Pode ser obrigatório por regra interna
    private List<Record0400> records0400;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedInnerObject
    //Pode ser obrigatório por regra interna
    private List<Record0450> records0450;

    @Transient
    @SpedInnerObject(required = true)
    private Record0990 record0990;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Bookkeeping.ID, nullable = false)
    private Bookkeeping bookkeeping;
}