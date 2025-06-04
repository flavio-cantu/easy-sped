package com.cantuaria.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * O domínio dessa classe deve vir do sistema legado
 * TODO alerta de carga!
 * Atenção! Quando for fazer a carga, garanta unicidade nos valores.
 */

@Entity
@Table(name = "UME_UNIDADE_MEDIDA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unity {

    public static final String ID = "UME_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Segundo o guia, a unidade é do sistema e é referenciado no item
     */
    @Column(name = "UME_CD_UNIDADE", length = 6, nullable = false, unique = true)
    private String spedCode;

    @Column(name = "UME_DS_NOME", length = 15, nullable = false, unique = true)
    private String name;


}
