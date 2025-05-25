package com.cantuaria.item;

import jakarta.persistence.*;

/**
 * O domínio dessa classe deve vir do sistema legado
 * TODO alerta de carga!
 * Atenção! Quando for fazer a carga, garanta unicidade nos valores.
 * No EFD de exemplo da evolução as unidades estão todas zuadas
 *
 */

@Entity
@Table(name = "UME_UNIDADE_MEDIDA")
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
    private String code;

    @Column(name = "UME_DS_NOME", length = 15, nullable = false, unique = true)
    private String name;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

}
