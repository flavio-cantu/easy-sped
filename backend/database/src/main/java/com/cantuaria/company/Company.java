package com.cantuaria.company;

import com.cantuaria.sped.domain.municipio.Municipality;
import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Classe com os dados da empresa.
 * Informações necessárias para escrituração
 */
@Entity
@Table(name = "EMP_EMPRESA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    public static final String ID = "EMP_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SpedValidation(validation = "REGRA_DIGITO_CNPJ", label = "CNPJ Empresa",
            description = "Número de inscrição do escritório de contabilidade no CNPJ")
    @Column(name = "EMP_DS_CNPJ", length = 14, nullable = false)
    private String cnpj;

    @Column(name = "EMP_DS_CEP", length = 8)
    private String cep;

    @Column(name = "EMP_DS_ENDERECO", length = 60)
    private String address;

    @Column(name = "EMP_DS_NUM", length = 10)
    private String number;

    @Column(name = "EMP_DS_COMPLEMENTO", length = 60)
    private String complement;

    @Column(name = "EMP_DS_BAIRRO", length = 60, nullable = false)
    private String neighborhood;

    /**
     * Código do município do domicílio fiscal da entidade, conforme a tabela IBGE
     * Infelizmente não temos no sped o vinculo com município. Mas seria bom conseguir.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Municipality.ID, nullable = false)
    private Municipality municipality;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Accountant> accountants;

}
