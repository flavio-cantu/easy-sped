package com.cantuaria.nf_layout;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe parametro com a configuração de CNPJ e quebra XML
 */
@Entity
@Table(name = "CLP_CLIENTE_NF_LAYOUT_PARAM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientNfLayout {
    public static final String ID = "CLP_ID";

    @Id
    @Column(name = ID)
    private String cnpj;

    @Column(name = "CLP_IMP")
    private String classPath;
}
