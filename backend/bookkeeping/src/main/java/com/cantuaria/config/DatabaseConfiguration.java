package com.cantuaria.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * O módulo da escrituração realmente vai usar toda a base de dados
 * porque é o final do processo e precisa de todas as informações
 *
 * Mas para otimizar o projeto carregue apenas o que o projeto precise.
 * Baixe o módulo de database para mais detalhes
 *
 */
@Configuration
@EntityScan(basePackages = {
        "com.cantuaria"
})
@EnableJpaRepositories(basePackages = {
        "com.cantuaria"
})
public class DatabaseConfiguration {
}
