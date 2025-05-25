package com.cantuaria.broker.processor;

import com.cantuaria.nf_layout.ClientNfLayout;
import com.cantuaria.nf_layout.ClientNfLayoutRepository;
import com.cantuaria.fiscal.NotaFiscal;
import com.cantuaria.fiscal.FiscalNoteRepository;
import com.cantuaria.broker.processor.layout.LayoutNotaFiscal;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class XmlProcessorService extends LayoutProcessor{

    private static final Logger LOG = LoggerFactory.getLogger(XmlProcessorService.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FiscalNoteRepository xmlDataRepository;
    @Autowired
    private ClientNfLayoutRepository clientNfLayoutRepository;

    @Transactional
    public void processXml(String xmlContent, String s3Key) {
        try {
            NotaFiscal data = parseXml(xmlContent);
            data.setCaminhoArquivo(s3Key);
            xmlDataRepository.save(data);
        } catch (Exception e) {
            LOG.error("Erro ao processar XML", e);
        }
    }

    private NotaFiscal parseXml(String xmlContent) throws JsonProcessingException, ClassNotFoundException {
        Map xmlMap = XML_MAPPER.readValue(xmlContent, Map.class);
        Map nfe = asMap(xmlMap, "NFe");
        Map infNFe = asMap(nfe, "infNFe");
        Map emitMap = asMap(infNFe, "emit");
        String cnpj = asString(emitMap, "CNPJ");

        ClientNfLayout clientNfLayout = clientNfLayoutRepository.findById(cnpj)
                .orElseThrow(() -> new RuntimeException("Cnpj Emissor não não cadastrado: " + cnpj));

        LayoutNotaFiscal layoutNotaFiscal = (LayoutNotaFiscal)applicationContext.getBean(Class.forName(clientNfLayout.getClassPath()));

        return layoutNotaFiscal.parseXml(xmlMap);
    }
}
