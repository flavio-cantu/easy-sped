package com.cantuaria.participant;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Classe representando os dados de histórico do participante.
 * Precisamos dessa informação para representar o registro 0175 do EFD
 */
@Entity
@Table(name = "PAH_PARTICIPANTE_HISTORY")
public class ParticipantHistory {
    public static final String ID = "PAH_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PAH_DH_ALTERACAO", nullable = false)
    private LocalDateTime changeDateTime;

    @Column(name = "PAH_CD_CAMPO", length = 2, nullable = false, columnDefinition = "varchar(2)")
    @Convert(converter = FieldParticipantConverter.class)
    private FieldParticipant fieldParticipant;

    @Column(name = "PAH_DS_VALOR_ANTERIOR", length = 100, nullable = false)
    private String previousValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Participant.ID, nullable = false)
    private Participant participant;
}
