package com.cantuaria.updater.param;

import com.cantuaria.updater.file.S3Service;
import com.cantuaria.updater.record.NotaFiscal;
import com.cantuaria.updater.record.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;

@Service
public class ParameterService {

    public static final String WATCH_DIR = "WATCH_DIR";

    @Autowired
    private ParameterRepository repository;


    @Transactional(readOnly = true)
    public String getWatchDir() {
        return repository.findById(WATCH_DIR)
                .map(Parameter::getValue)
                .orElse(null);
    }

    @Transactional
    public void changeWatchDir(String absolutePath) {
        Parameter parameter = repository.findById(WATCH_DIR)
                .orElse(new Parameter(WATCH_DIR));
        parameter.setValue(absolutePath);
        repository.save(parameter);
    }
}
