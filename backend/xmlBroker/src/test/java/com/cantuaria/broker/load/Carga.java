package com.cantuaria.broker.load;

import com.cantuaria.nf_layout.ClientNfLayout;
import com.cantuaria.nf_layout.ClientNfLayoutRepository;
import com.cantuaria.broker.processor.layout.PostoCombustivelCidadeOcidentalGO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Carga implements CommandLineRunner {

    @Autowired
    private ClientNfLayoutRepository repository;

    @Override
    public void run(String... args) throws Exception {
        ClientNfLayout clientNfLayout = new ClientNfLayout();
        clientNfLayout.setCnpj("18571325000190");
        clientNfLayout.setClassPath(PostoCombustivelCidadeOcidentalGO.class.getCanonicalName());
        repository.save(clientNfLayout);
    }

}
