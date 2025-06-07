package com.cantuaria.consolidate;

import com.cantuaria.client.Client;
import com.cantuaria.company.Accountant;
import com.cantuaria.company.Company;
import com.cantuaria.item.Item;
import com.cantuaria.item.ItemHistory;
import com.cantuaria.item.Unity;
import com.cantuaria.participant.Participant;
import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.sped.block_0.Block0;
import com.cantuaria.sped.block_0.Record0000;
import com.cantuaria.sped.block_0.Record0002;
import com.cantuaria.sped.block_0.Record0005;
import com.cantuaria.sped.block_0.Record0100;
import com.cantuaria.sped.block_0.Record0150;
import com.cantuaria.sped.block_0.Record0190;
import com.cantuaria.sped.block_0.Record0200;
import com.cantuaria.sped.block_0.Record0205;
import com.cantuaria.sped.domain.ActivityType;
import com.cantuaria.util.DateUtil;
import com.cantuaria.validation.BusinessValidation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        parseRecord0005(bookkeeping, block0);
        parseRecord0100(bookkeeping, block0);
        parseRecord0150(bookkeeping, block0);
        parseRecord0190(bookkeeping, block0);
        parseRecord0200(bookkeeping, block0);

        return validation;
    }

    private void parseRecord0200(Bookkeeping bookkeeping, Block0 block0) {
        List<Record0200> listRecord = Optional.ofNullable(block0.getRecords0200())
                .orElseGet(() -> {
                    List<Record0200> records = new ArrayList<>();
                    block0.setRecords0200(records);
                    return block0.getRecords0200();
                });

        List<Item> clientItens = bookkeeping.getClient().getItens();

        //check if the list matches
        //For remove
        List<String> itemCodes = clientItens.stream().map(Item::getItemCode).toList();
        List<Record0200> remove = new ArrayList<>();
        for (Record0200 record : listRecord) {
            if (!itemCodes.contains(record.getCodItem())) {
                remove.add(record);
            }
        }
        listRecord.removeAll(remove);


        //For add
        Map<String, Record0200> mapRecords = listRecord.stream()
                .collect(Collectors.toMap(Record0200::getCodItem, Function.identity()));
        for (Item bdItem : clientItens) {
            Record0200 record = mapRecords.getOrDefault(bdItem.getItemCode(), new Record0200());

            record.setCodItem(bdItem.getItemCode());
            record.setDescrItem(bdItem.getDescription());
            record.setCodBarra(bdItem.getBarCode());
            record.setUnidInv(bdItem.getUnity().getSpedCode());
            record.setTipoItem(bdItem.getItemType().getCode());
            record.setCodNCM(bdItem.getMercosulCode());
            record.setExIPI(bdItem.getExTipiCode());
            record.setCodGen(bdItem.getGender().getSpedCode());
            record.setCodLST(bdItem.getServiceCode());
            record.setAliqICMS(bdItem.getAliquotICMS());
            record.setCest(bdItem.getCest().getSpedCode());
            parseRecord0205(bookkeeping, bdItem, record, block0);
            //TODO record.setRecord0220();
            //TODO record.setRecord0221();

            if (record.getId() == null) {
                record.setBlock(block0);
                listRecord.add(record);
            }
        }
    }

    private void parseRecord0205(Bookkeeping bookkeeping, Item item, Record0200 record0200, Block0 block0) {
        List<Record0205> listRecord = Optional.ofNullable(record0200.getRecords0205())
                .orElseGet(() -> {
                    List<Record0205> records = new ArrayList<>();
                    record0200.setRecords0205(records);
                    return record0200.getRecords0205();
                });

        List<ItemHistory> histories = item.getHistories()//MVP
                .stream().filter(itemHistory ->
                        itemHistory.getEndDate().isAfter(bookkeeping.getStart())
                                && itemHistory.getEndDate().isBefore(bookkeeping.getEnd())
                ).toList();

        //Historico não tem nada que nos ajude a ientar no EDF
        listRecord.clear();

        //For add
        for (ItemHistory history : histories) {
            Record0205 record = new Record0205();

            record.setDescrAntItem(history.getPreviousDescription());
            record.setDtIni(DateUtil.toEDFFormat(history.getStartDate()));
            record.setDtFim(DateUtil.toEDFFormat(history.getEndDate()));
            record.setCodAntItem(record.getCodAntItem());

            record.setRecord0200(record0200);
            record.setBlock(block0);
            listRecord.add(record);
        }
    }


    private void parseRecord0190(Bookkeeping bookkeeping, Block0 block0) {
        List<Record0190> listRecord = Optional.ofNullable(block0.getRecords0190())
                .orElseGet(() -> {
                    List<Record0190> records = new ArrayList<>();
                    block0.setRecords0190(records);
                    return block0.getRecords0190();
                });

        List<Unity> clientUnities = bookkeeping.getClient().getUnities();

        //check if the list matches
        //For remove
        List<String> spedCodes = clientUnities.stream().map(Unity::getSpedCode).toList();
        List<Record0190> remove = new ArrayList<>();
        for (Record0190 record : listRecord) {
            if (!spedCodes.contains(record.getUnid())) {
                remove.add(record);
            }
        }
        listRecord.removeAll(remove);


        //For add
        Map<String, Record0190> mapRecords = listRecord.stream()
                .collect(Collectors.toMap(Record0190::getUnid, Function.identity()));
        for (Unity bdUnity : clientUnities) {
            Record0190 record = mapRecords.getOrDefault(bdUnity.getSpedCode(), new Record0190());

            record.setUnid(bdUnity.getSpedCode());
            record.setDescr(bdUnity.getName());

            if (record.getId() == null) {
                record.setBlock(block0);
                listRecord.add(record);
            }
        }
    }

    private void parseRecord0150(Bookkeeping bookkeeping, Block0 block0) {
        List<Record0150> listRecord = Optional.ofNullable(block0.getRecords0150())
                .orElseGet(() -> {
                    List<Record0150> record0150s = new ArrayList<>();
                    block0.setRecords0150(record0150s);
                    return block0.getRecords0150();
                });

        List<Participant> clientParticipants = bookkeeping.getClient().getParticipants();

        //check if the list matches
        //For remove
        List<String> cnpjParticipants = clientParticipants.stream().map(Participant::getCnpj).toList();
        List<Record0150> remove = new ArrayList<>();
        for (Record0150 record0150 : listRecord) {
            if (!cnpjParticipants.contains(record0150.getCnpj())) {
                remove.add(record0150);
            }
        }
        listRecord.removeAll(remove);


        //For add
        Map<String, Record0150> cnpjRecords = listRecord.stream()
                .collect(Collectors.toMap(Record0150::getCnpj, Function.identity()));
        for (Participant bdParticipant : clientParticipants) {
            Record0150 record = cnpjRecords.getOrDefault(bdParticipant.getCnpj(), new Record0150());

            record.setCodPart(bdParticipant.getCode());
            record.setNome(bdParticipant.getName());
            record.setCodPais(bdParticipant.getCountry().getSpedCode());
            record.setCnpj(bdParticipant.getCnpj());
            record.setCpf(bdParticipant.getCpf());
            record.setInscricaoEstadual(bdParticipant.getStateRegistration());
            record.setCodMun(bdParticipant.getMunicipality().getSpedCode());
            record.setSuframa(bdParticipant.getSuframa());
            record.setEndereco(bdParticipant.getAddress());
            record.setNum(bdParticipant.getNumber());
            record.setCompl(bdParticipant.getComplement());
            record.setBairro(bdParticipant.getNeighborhood());

            if (record.getId() == null) {
                record.setBlock(block0);
                listRecord.add(record);
            }
        }

    }

    private void parseRecord0100(Bookkeeping bookkeeping, Block0 block0) {
        Record0100 record = Optional.ofNullable(block0.getRecord0100())
                .orElseGet(Record0100::new);
        block0.setRecord0100(record);
        record.setBlock(block0);

        Accountant accountant = bookkeeping.getAccountant();
        Company company = bookkeeping.getCompany();

        record.setNome(accountant.getName());
        record.setCpf(accountant.getCpf());
        record.setCrc(accountant.getCrc());
        record.setCnpj(company.getCnpj());
        record.setCep(company.getCep());
        record.setEndereco(company.getAddress());
        record.setCep(company.getCep());
        record.setNum(company.getNumber());
        record.setCompl(company.getComplement());
        record.setBairro(company.getNeighborhood());
        record.setFone(accountant.getPhone());
        record.setFax(accountant.getFax());
        record.setEmail(accountant.getEmail());
        record.setCodMun(company.getMunicipality().getSpedCode());

    }

    private void parseRecord0005(Bookkeeping bookkeeping, Block0 block0) {
        Record0005 record = Optional.ofNullable(block0.getRecord0005())
                .orElseGet(Record0005::new);
        block0.setRecord0005(record);
        record.setBlock(block0);

        Client client = bookkeeping.getClient();

        record.setFantasia(client.getFantasyName());
        record.setCep(client.getCep());
        record.setEndereco(client.getAddress());
        record.setNum(client.getNumber());
        record.setCompl(client.getComplement());
        record.setBairro(client.getNeighborhood());
        record.setFone(client.getPhone());
        record.setFax(client.getFax());
        record.setEmail(client.getEmail());
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
