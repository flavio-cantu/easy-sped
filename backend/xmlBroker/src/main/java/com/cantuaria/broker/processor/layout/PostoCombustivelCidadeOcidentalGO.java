package com.cantuaria.broker.processor.layout;

import com.cantuaria.fiscal.NotaFiscal;
import com.cantuaria.fiscal.nfe.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class PostoCombustivelCidadeOcidentalGO extends LayoutNotaFiscal {

    @Override
    public NotaFiscal parseXml(Map map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        NotaFiscal notaFiscal = new NotaFiscal();

        Map nfe = asMap(map, "NFe");
        Map infNFe = asMap(nfe, "infNFe");
        notaFiscal.setId(asString(infNFe, "Id"));

        Map ideMap = asMap(infNFe, "ide");
        IDE ide = objectMapper.readValue(
                objectMapper.writeValueAsString(ideMap), IDE.class);
        notaFiscal.setIde(ide);
        ide.setNotaFiscal(notaFiscal);

        Map emitMap = asMap(infNFe, "emit");
        Map enderEmit = asMap(emitMap, "enderEmit");
        emitMap.remove("enderEmit");
        Emit emit = objectMapper.readValue(
                objectMapper.writeValueAsString(asUnitedMap(emitMap, enderEmit))
                , Emit.class);
        notaFiscal.setEmit(emit);
        emit.setNotaFiscal(notaFiscal);

        Map autXML = asMap(infNFe, "autXML");
        notaFiscal.setAutXmlCnpj(asString(autXML, "CNPJ"));

        for (Map item : asList(infNFe, "det")) {
            Map fields = new LinkedHashMap();
            fields.put("nItem", item.get("nItem"));

            Map prod = asMap(item, "prod");
            Map comb = asMap(prod, "comb");
            prod.remove("comb");

            asUnitedMap(fields, prod);

            Det det = objectMapper.readValue(objectMapper.writeValueAsString(fields), Det.class);

            notaFiscal.getDets().add(det);
            det.setNotaFiscal(notaFiscal);

            Map encerrante = asMap(comb, "encerrante");
            comb.remove("encerrante");

            Comb combustivel = objectMapper.readValue(objectMapper.writeValueAsString(asUnitedMap(comb, encerrante)), Comb.class);

            det.setComb(combustivel);
            combustivel.setDet(det);

            Map impostoMap = asMap(item, "imposto");
            Map icms = asMap(asMap(impostoMap, "ICMS"), "ICMS61");
            Map pis = asMap(asMap(impostoMap, "PIS"), "PISNT");
            Map cofins = asMap(asMap(impostoMap, "COFINS"), "COFINSNT");

            Imposto imposto = new Imposto();
            det.setImposto(imposto);
            imposto.setDet(det);

            imposto.setIcmsOrig(asString(icms, "orig"));
            imposto.setIcmsCST(asString(icms, "CST"));
            imposto.setIcmsqBCMonoRet(asDouble(icms, "qBCMonoRet"));
            imposto.setIcmsadRemICMSRet(asDouble(icms, "adRemICMSRet"));
            imposto.setIcmsvICMSMonoRet(asDouble(icms, "vICMSMonoRet"));

            imposto.setPisCST(asString(pis, "CST"));
            imposto.setConfinsCST(asString(cofins, "CST"));
        }

        Map icmsTotal = asMap(asMap(infNFe, "total"), "ICMSTot");
        Total total = objectMapper.readValue(objectMapper.writeValueAsString(icmsTotal), Total.class);
        notaFiscal.setTotal(total);
        total.setNotaFiscal(notaFiscal);


        Map infoAdicional = new LinkedHashMap();
        asUnitedMap(infoAdicional, asMap(infNFe, "transp"));
        asUnitedMap(infoAdicional, asMap(asMap(infNFe, "pag"), "detPag"));
        asUnitedMap(infoAdicional, asMap(infNFe, "infRespTec"));
        asUnitedMap(infoAdicional, asMap(nfe, "infNFeSupl"));
        asUnitedMap(infoAdicional, asMap(asMap(map, "protNFe"), "infProt"));

        InformacaoAdicional informacaoAdicional = objectMapper.readValue(objectMapper.writeValueAsString(infoAdicional), InformacaoAdicional.class);
        informacaoAdicional.setNotaFiscal(notaFiscal);
        notaFiscal.setInformacaoAdicional(informacaoAdicional);

        return notaFiscal;
    }

}
