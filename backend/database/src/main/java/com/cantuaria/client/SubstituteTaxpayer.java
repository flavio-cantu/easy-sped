package com.cantuaria.client;

import com.cantuaria.sped.domain.UF;
import jakarta.persistence.*;

/**
 * Representação da ocorrencia registro id="0015" do sped
 * Não é obrigatório ter, mas se possuir os dados são obrigatórios
 */
@Entity
@Table(name = "COS_CONTRIBUINTE_SUBSTITUTO")
public class SubstituteTaxpayer {
    public static final String ID = "COS_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COS_CD",length = 2, nullable = false, columnDefinition = "varchar(2)")
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(name = "COS_DS_INSCRICAO_ESTADUAL", length = 14, nullable = false)
    private String municipalRegistration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Client.ID, nullable = false)
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getMunicipalRegistration() {
        return municipalRegistration;
    }

    public void setMunicipalRegistration(String municipalRegistration) {
        this.municipalRegistration = municipalRegistration;
    }
}
