package com.cantuaria.participant;

import com.cantuaria.client.Client;
import com.cantuaria.sped.domain.country.Country;
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
 * Classe representando os dados do participante
 */
@Entity
@Table(name = "PAR_PARTICIPANTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant { //TODO alerta de carga!
    public static final String ID = "PAR_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código do sistema de identificação do participante no arquivo
     */
    @Column(name = "PAR_CD_PARTICIPANTE", length = 60, nullable = false)
    private String code;

    @Column(name = "PAR_DS_NOME", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Country.ID, nullable = false)
    private Country country;

    @SpedValidation(validation = "REGRA_DIGITO_CNPJ", label = "CNPJ Participante",
            description = "CNPJ do participante")
    @Column(name = "PAR_DS_CNPJ", length = 14, unique = true)
    private String cnpj;

    @SpedValidation(validation = "REGRA_DIGITO_CPF", label = "CPF",
            description = "CPF do participante")
    @Column(name = "PAR_DS_CPF", length = 11, unique = true)
    private String cpf;

    @Column(name = "PAR_DS_IE", length = 14)
    private String stateRegistration;

    /**
     * Código do município do domicílio fiscal da entidade, conforme a tabela IBGE
     * Infelizmente não temos no sped o vinculo com município. Mas seria bom conseguir.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Municipality.ID, nullable = false)
    private Municipality municipality;

    @SpedValidation(validation = "REGRA_DIGITO_SUFRAMA", label = "Inscrição na SUFRAMA",
            description = "Inscrição da entidade na SUFRAMA")
    @Column(name = "PAR_DS_SUFRAMA", length = 9)
    private String suframa;

    @Column(name = "PAR_DS_ENDERECO", length = 60, nullable = false)
    private String address;

    @Column(name = "PAR_DS_NUM", length = 10)
    private String number;

    @Column(name = "PAR_DS_COMPLEMENTO", length = 60)
    private String complement;

    @Column(name = "PAR_DS_BAIRRO", length = 60, nullable = false)
    private String neighborhood;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ParticipantHistory> histories;

    @ManyToMany(mappedBy = "participants")
    private List<Client> clients;
}
