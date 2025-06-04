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

/**
 * Representação do registro 0001 do arquivo de escrituração
 * Nem é uma entidade porque só possui dados constantes ou calculados
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record0001 {
    public static final String REG = "0001";
    private boolean block0HasInfo;
}