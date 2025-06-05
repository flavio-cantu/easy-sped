package com.cantuaria.feature;

import com.cantuaria.AppBookkeeping;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@CucumberContextConfiguration
@ContextConfiguration(classes = StubFeatureConfiguration.class)
@SpringBootTest(classes = AppBookkeeping.class)
@AutoConfigureMockMvc
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/", glue = "com.cantuaria")
@ActiveProfiles("feature")
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.h2.console.enabled=true",
        "spring.h2.console.settings.trace=true",
        "spring.jpa.show-sql=true"
})
public class CucumberTest {


}
