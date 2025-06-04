package com.cantuaria.sped.domain.nature;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Código de natureza da operação
 * Informação retirada do item 0400 do guia:
 * Este registro tem por objetivo codificar os textos das diferentes naturezas da operação/prestações discriminadas nos
 * documentos fiscais. Esta codificação e suas descrições são livremente criadas e mantidas pelo contribuinte.
 * Este registro não se refere ao CFOP.
 * <p>
 * TODO entidade sem vinculo, mas utiliado no C170
 */

@Entity
@Table(name = "NAT_NATUREZA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nature {

    public static final String ID = "NAT_ID";

    @Id
    @Column(name = ID, length = 10)
    private String code;

    @Column(name = "NAT_DS_NOME", length = 300, nullable = false)
    private String name;

}
