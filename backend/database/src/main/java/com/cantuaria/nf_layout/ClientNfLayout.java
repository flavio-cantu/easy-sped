package com.cantuaria.nf_layout;

import jakarta.persistence.*;

/**
 * Classe parametro com a configuração de CNPJ e quebra XML
 */
@Entity
@Table(name = "CLP_CLIENTE_NF_LAYOUT_PARAM")
public class ClientNfLayout {
    public static final String ID = "CLP_ID";

    @Id
    @Column(name = ID)
    private String cnpj;

    @Column(name = "CLP_IMP")
    private String classPath;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
}
