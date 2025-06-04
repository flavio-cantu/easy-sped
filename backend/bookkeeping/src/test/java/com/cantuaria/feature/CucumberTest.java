package com.cantuaria.feature;

import com.cantuaria.AppBookkeeping;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = StubFeatureConfiguration.class)
@SpringBootTest(classes = AppBookkeeping.class)
@AutoConfigureMockMvc
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/", glue = "com.cantuaria")
@ActiveProfiles("feature")
public class CucumberTest {


}
