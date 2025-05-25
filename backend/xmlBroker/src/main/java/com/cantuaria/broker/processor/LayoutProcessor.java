package com.cantuaria.broker.processor;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LayoutProcessor {

    protected XmlMapper XML_MAPPER = new XmlMapper();

    protected Map asMap(Map map, String key){
        return (Map)map.get(key);
    }

    protected Map asUnitedMap(Map mainMap, Map aditionalMap) {
        for (Object key : aditionalMap.keySet()) {
            mainMap.put(key, aditionalMap.get(key));
        }
        return mainMap;
    }

    protected List<Map> asList(Map map, String key){
        return (List<Map>)map.get(key);
    }

    protected String asString(Map map, String key){
        if(map == null){
            return "";
        }
        return (String)map.get(key);
    }

    protected Double asDouble(Map map, String key){
        if(map == null){
            return 0.0;
        }
        return Double.parseDouble(asString(map,key));
    }
}
