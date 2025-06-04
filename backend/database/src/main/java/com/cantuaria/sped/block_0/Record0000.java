package com.cantuaria.sped.block_0;

import com.cantuaria.sped.domain.ActivityType;
import com.cantuaria.sped.domain.Profile;
import com.cantuaria.sped.domain.Purpose;
import com.cantuaria.sped.domain.UF;
import com.cantuaria.sped.domain.layout_version.LayoutVersionRepository;
import com.cantuaria.sped.domain.municipio.MunicipalityRepository;
import com.cantuaria.validation.SpedDatabaseValidation;
import com.cantuaria.validation.SpedEnumValidation;
import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representação do registro 0000 do arquivo de escrituração
 * <p>
 * Basicamente o registro 0000 trata das informações do cliente
 * <p>
 * Essa entidade é retratada exatamente como no descriptor e no guia.
 * Para facilitar a rastreabilidade dos campos as colunas de banco serão a referencia.
 */
@Entity
@Table(name = "B0R0000_BLOCO_0_REGISTRO_0000")
@SpedValidation(validation = {
        "REGRA_VALIDA_IE_0000_V2",
        "REGRA_OBRIGATORIO_CPF_EXCLUDENTE_0000_IMPORTACAO",
        "REGRA_VALIDA_IND_ATIV",
        "REGRA_OBRIGATORIO_CPF_EXCLUDENTE_0000_CONTEUDO",
        "REGRA_VERSAO_LEIAUTE",
        "REGRA_DATA_MINIMA",
        "REGRA_MES_ESCRITURACAO",
        "REGRA_DATA_MENOR_IGUAL_DT_FIN",
        "REGRA_COD_MUN_EM_UF_V2",
        "REGRA_EXISTE_APURACAO_IPI",
        "REGRA_NAO_EXISTE_APURACAO_IPI"
}, label = "Identificação", description = "Dados Cadastrais do Informante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0000 {

    public static final String REG = "0000";
    public static final String ID = "B0R0000_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Origem Bookkeeping.layoutVersion
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = LayoutVersionRepository.class,
            label = "Código da versão do leiaute", description = "Código da versão do leiaute conforme Tabela Versão do Leiaute")
    @Column(name = "COD_VER", length = 3, nullable = false)
    private String codVer;

    //Origem Bookkeeping.purpose
    @SpedEnumValidation(validation = "CODIGO_EXISTE_ENUM", enumType = Purpose.class,
            label = "Código da finalidade", description = "Data inicial das informações contidas no arquivo")
    @Column(name = "COD_FIN", length = 1, nullable = false)
    private String codFin;

    //Origem Bookkeeping.start (Formato: ddmmaaaa)
    @SpedValidation(validation = {"REGRA_DATA_VALIDA_A4E5V1"}, label = "Data inicial",
            description = "Data inicial das informações contidas no arquivo")
    @Column(name = "DT_INI", length = 8, nullable = false)
    private String dtIni;

    //Origem Bookkeeping.end (Formato: ddmmaaaa)
    @SpedValidation(validation = {"REGRA_DATA_VALIDA_A4E5V1"}, label = "Data inicial",
            description = "Data final das informações contidas no arquivo")
    @Column(name = "DT_FIN", length = 8, nullable = false)
    private String dtFim;

    //Origem Client.businessName OU Client.responsibleName
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    //Origem Client.cnpj
    @SpedValidation(validation = "REGRA_DIGITO_CNPJ", label = "CNPJ Cliente",
            description = "Número de inscrição da entidade no CNPJ")
    @Column(name = "CNPJ", length = 14)
    private String cnpj;

    //Origem Client.responsibleCpf
    @SpedValidation(validation = "REGRA_DIGITO_CPF", label = "CPF Responsável",
            description = "Número de inscrição da pessoa natural no CPF")
    @Column(name = "CPF", length = 11)
    private String cpf;

    //Origem Client.uf
    @Column(name = "UF", length = 2, nullable = false)
    @SpedEnumValidation(validation = "CODIGO_EXISTE_ENUM", enumType = UF.class,
            label = "UF", description = "Sigla da unidade da federação da entidade")
    private String uf;

    //Origem Client.stateRegistration
    @Column(name = "IE", length = 14, nullable = false)
    private String ie;

    //Origem Client.municipality
    @SpedDatabaseValidation(validation = "CODIGO_EXISTE_DATABASE", databaseType = MunicipalityRepository.class,
            label = "Código do município", description = "Código do município do domicílio fiscal da entidade, conforme a tabela IBGE")
    @Column(name = "COD_MUN", length = 7, nullable = false)
    private String codMun;

    //Origem Client.municipalRegistration
    @Column(name = "IM", length = 14)
    private String im;

    //Origem Client.suframa
    @SpedValidation(validation = "REGRA_DIGITO_SUFRAMA", label = "Inscrição na SUFRAMA",
            description = "Inscrição da entidade na SUFRAMA")
    @Column(name = "SUFRAMA", length = 9)
    private String suframa;

    //Origem Bookkeeping.profile
    @SpedEnumValidation(validation = "CODIGO_EXISTE_ENUM", enumType = Profile.class,
            label = "Perfil do arquivo fiscal", description = "Perfil de apresentação do arquivo fiscal")
    @Column(name = "IND_PERFIL", length = 1, nullable = false)
    private String profile;

    //Origem Client.activityType
    @SpedEnumValidation(validation = "CODIGO_EXISTE_ENUM", enumType = ActivityType.class,
            label = "Tipo de atividade", description = "Indicador de tipo de atividade")
    @Column(name = "IND_ATIV", length = 1, nullable = false)
    private Integer activityType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Block0.ID, nullable = false)
    private Block0 block;
}