package com.cantuaria.consolidate;

import com.cantuaria.client.Client;
import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.sped.block_0.Block0;
import com.cantuaria.sped.block_0.Record0000;
import com.cantuaria.sped.block_0.Record0002;
import com.cantuaria.sped.domain.ActivityType;
import com.cantuaria.util.DateUtil;
import com.cantuaria.validation.BusinessValidation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Component
public class Block0Service {

    @Transactional
    public BusinessValidation parse(Bookkeeping bookkeeping) {
        BusinessValidation validation = new BusinessValidation();
        Block0 block0 = Optional.ofNullable(bookkeeping.getBlock0())
                .orElseGet(Block0::new);
        bookkeeping.setBlock0(block0);
        block0.setBookkeeping(bookkeeping);

        Record0000 record0000 = parseRecord0000(bookkeeping, block0);
        if (Objects.equals(record0000.getActivityType(), ActivityType.INDUSTRIAL.getCode())) {
            parseRecord0002(bookkeeping, block0);
        }


        return validation;
    }

    private void parseRecord0002(Bookkeeping bookkeeping, Block0 block0) {
        Record0002 record = Optional.ofNullable(block0.getRecord0002())
                .orElseGet(Record0002::new);
        block0.setRecord0002(record);
        record.setBlock(block0);

        record.setClasEstabInd(bookkeeping.getClient().getClassification().getSpedCode());
    }

    private Record0000 parseRecord0000(Bookkeeping bookkeeping, Block0 block0) {
        Record0000 record = Optional.ofNullable(block0.getRecord0000())
                .orElseGet(Record0000::new);
        block0.setRecord0000(record);
        record.setBlock(block0);

        record.setCodVer(bookkeeping.getLayoutVersion().getSpedCode());
        record.setCodFin(bookkeeping.getPurpose().getCode());
        record.setDtIni(DateUtil.toEDFFormat(bookkeeping.getStart()));
        record.setDtFim(DateUtil.toEDFFormat(bookkeeping.getEnd()));

        Client client = bookkeeping.getClient();
        if (client.getCnpj() != null) {
            record.setNome(client.getBusinessName());
            record.setCnpj(client.getCnpj());
        } else if (client.getResponsibleCpf() != null) {
            record.setNome(client.getResponsibleName());
            record.setCpf(client.getResponsibleCpf());
        }

        record.setUf(client.getUf().getCode());
        record.setIe(client.getStateRegistration());
        record.setCodMun(client.getMunicipality().getSpedCode());
        record.setIm(client.getMunicipalRegistration());
        record.setSuframa(client.getSuframa());
        record.setProfile(bookkeeping.getProfile().getCode());
        record.setActivityType(client.getActivityType().getCode());
        return record;
    }


}
