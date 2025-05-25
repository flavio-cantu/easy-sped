package com.cantuaria.broker.processor.layout;

import com.cantuaria.fiscal.NotaFiscal;
import com.cantuaria.broker.processor.LayoutProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public abstract class LayoutNotaFiscal extends LayoutProcessor {

    public abstract NotaFiscal parseXml(Map mapXml) throws JsonProcessingException;

}
