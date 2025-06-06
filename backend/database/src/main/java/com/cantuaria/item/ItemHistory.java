package com.cantuaria.item;

import com.cantuaria.validation.SpedValidation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Classe representando os dados de histórico do item.
 *
 * Observações: Os campos 02 e 05 são mutuamente excludentes, sendo obrigatório o preenchimento de um deles. Em caso de
 * alteração da ITH_DS_ANTERIOR e do ITH_CD_ANTERIOR deverá ser gerado um registro para cada alteração
 *
 * Precisamos dessa informação para representar o registro 0205 do EFD
 */
@Entity
@Table(name = "ITH_ITEM_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemHistory {
    public static final String ID = "ITH_ID";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SpedValidation(validation = {"REGRA_DATA_VALIDA_A4E5V1", "REGRA_DATA_MINIMA_DOC_FISCAL"}, label = "Data inicial",
            description = "Data inicial de utilização da descrição do item")
    @Column(name = "ITH_DT_INICIO", nullable = false)
    private LocalDate startDate;

    @SpedValidation(validation = {"REGRA_DATA_VALIDA_A4E5V1", "REGRA_DATA_MINIMA_DOC_FISCAL"}, label = "Data final",
            description = "Data final de utilização da descrição do item")
    @Column(name = "ITH_DT_FIM", nullable = false)
    private LocalDate endDate;

    @Column(name = "ITH_DS_ANTERIOR", length = 200)
    private String previousDescription;

    @Column(name = "ITH_CD_ANTERIOR", length = 60)
    private String previousCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = Item.ID, nullable = false)
    private Item item;
}
