package com.cantuaria.company;

import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe com os dados do contador.
 * Informações necessárias para escrituração
 */
@Entity
@Table(name = "CON_CONTABILISTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accountant {
    public static final String ID = "CON_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CON_DS_NOME", length = 60, nullable = false)
    private String name;

    @SpedValidation(validation = "REGRA_DIGITO_CPF", label = "CPF",
            description = "Número de inscrição do contabilista no CPF")
    @Column(name = "CON_DS_CPF", length = 11, nullable = false)
    private String cpf;

    @Column(name = "CON_DS_CRC", length = 15, nullable = false)
    private String crc;

    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Telefone",
            description = "Número do telefone")
    @Column(name = "CON_DS_TELEFONE", length = 11)
    private String phone;

    @SpedValidation(validation = "REGRA_VALIDA_TELEFONE_FAX", label = "Fax",
            description = "Número do fax")
    @Column(name = "CON_DS_FAX", length = 11)
    private String fax;

    @SpedValidation(validation = "REGRA_VALIDA_EMAIL_FISCAL", label = "E-mail",
            description = "Endereço do correio eletrônico")
    @Column(name = "CON_DS_EMAIL", length = 60, nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Company.ID, nullable = false)
    private Company company;
}
