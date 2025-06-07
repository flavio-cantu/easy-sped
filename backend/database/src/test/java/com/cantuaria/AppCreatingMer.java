package com.cantuaria;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("mer")
public class AppCreatingMer {

    @Test
    public void setup() {
        System.out.println("Banco criado");
    }

}
