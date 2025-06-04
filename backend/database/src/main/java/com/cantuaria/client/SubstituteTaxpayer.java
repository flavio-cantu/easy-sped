package com.cantuaria.client;

import com.cantuaria.sped.domain.UF;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação da ocorrencia registro id="0015" do sped
 * Não é obrigatório ter, mas se possuir os dados são obrigatórios
 */
@Entity
@Table(name = "COS_CONTRIBUINTE_SUBSTITUTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubstituteTaxpayer {
    public static final String ID = "COS_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COS_CD", length = 2, nullable = false, columnDefinition = "varchar(2)")
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(name = "COS_DS_INSCRICAO_ESTADUAL", length = 14, nullable = false)
    private String municipalRegistration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Client.ID, nullable = false)
    private Client client;

}
