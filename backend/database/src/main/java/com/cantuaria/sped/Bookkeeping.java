package com.cantuaria.sped;

import com.cantuaria.client.Client;
import com.cantuaria.sped.block_0.Block0;
import com.cantuaria.sped.block_1.Block1;
import com.cantuaria.sped.block_9.Block9;
import com.cantuaria.sped.block_b.BlockB;
import com.cantuaria.sped.block_c.BlockC;
import com.cantuaria.sped.block_d.BlockD;
import com.cantuaria.sped.block_e.BlockE;
import com.cantuaria.sped.block_g.BlockG;
import com.cantuaria.sped.block_h.BlockH;
import com.cantuaria.sped.block_k.BlockK;
import com.cantuaria.sped.domain.Profile;
import com.cantuaria.sped.domain.Purpose;
import com.cantuaria.sped.domain.converter.PurposeConverter;
import com.cantuaria.sped.domain.layout_version.LayoutVersion;
import com.cantuaria.sped.domain.observation.Observation;
import com.cantuaria.validation.SpedRequired;
import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pai que representa a escrituração do cliente
 * Ela será o espaço de trabalho para geração do EFD para o SPED
 * Diferente do sped, não vamos trabalhar com versões, o sistema sempre vai gerar o EDF na ultima versão disponível
 */
@Entity
@Table(name = "EDI_ESCRITURACAO_DIGITAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookkeeping {
    public static final String ID = "EDI_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Client.ID, nullable = false)
    private Client client;

    @SpedValidation(validation = "REGRA_DATA_VALIDA_A4E5V1", label = "Data inicial",
            description = "Data inicial das informações contidas no arquivo")
    @Column(name = "EDI_DT_INICIO", nullable = false)
    private LocalDate start;


    @SpedValidation(validation = "REGRA_DATA_VALIDA_A4E5V1", label = "Data final",
            description = "Data final das informações contidas no arquivo")
    @Column(name = "EDI_DT_FIM", nullable = false)
    private LocalDate end;

    /**
     * Código da versão do leiaute conforme Tabela Versão do Leiaute
     * O vinculo vai ficar aqui, mas é só um id, a versão na tabela vai ser atualizada quando o
     * sistema estiver pronto para a proxima versão no sped. dessa forma, todas as escriturações
     * já vão passar a exportar no modelo novo.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = LayoutVersion.ID, nullable = false)
    private LayoutVersion layoutVersion;

    /**
     * Código da finalidade da escrituração
     */
    @Column(name = "EDI_CD_PURPOSE", length = 1, nullable = false, columnDefinition = "varchar(1)")
    @Convert(converter = PurposeConverter.class)
    private Purpose purpose;

    /**
     * Perfil de apresentação do arquivo fiscal;
     */
    @Column(name = "EDI_CD_PERFIL", length = 1, nullable = false, columnDefinition = "varchar(1)")
    @Enumerated(EnumType.STRING)
    private Profile profile;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "OED_OBSERVACOES_ESCRITURACAO_DIGITAL",
            joinColumns = @JoinColumn(name = Bookkeeping.ID),
            inverseJoinColumns = @JoinColumn(name = Observation.ID)
    )
    private List<Observation> observations = new ArrayList<>();

    //Blocos da escrituracao =======================================
    //Compilação dos blocos para representar o arquivo EDF que futuramente será enviado a RFB

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private Block0 block0;

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private BlockB blockb;

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private BlockC blockC;

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private BlockD blockD;

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private BlockE blockE;

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private BlockG blockG;

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private BlockH blockH;

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private BlockK blockK;

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private Block1 block1;

    @OneToOne(mappedBy = "bookkeeping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @SpedRequired
    private Block9 block9;

}
