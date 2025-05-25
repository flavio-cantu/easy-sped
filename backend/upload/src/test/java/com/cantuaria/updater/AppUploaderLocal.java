package com.cantuaria.updater;


import com.cantuaria.updater.ui.AppTrayIcon;
import com.cantuaria.updater.upload.config.ParameterConfig;
import com.cantuaria.updater.upload.config.UploaderConfigGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class AppUploaderLocal {

    private static final Logger LOG = LoggerFactory.getLogger(AppUploaderLocal.class);

    public static void main(String[] args){
        new File(ParameterConfig.CONFIG_PATH).delete();
        try{
            System.setProperty("spring.profiles.active", "local");
            UploaderConfigGenerator.generateFromArgs(new String[]{"--configure","--key=KEY","--access=ACCESS","--region=us-east-1","--bucket=BUCKET", "--folder=posto","--watchFolder=target/."});

            ConfigurableApplicationContext context = new SpringApplicationBuilder(AppUploader.class)
                    .headless(false)
                    .web(WebApplicationType.NONE)
                    .run(args);

            Thread arquivosTeste = new Thread(() -> {
                try{
                    Thread.sleep(2000l);
                    LOG.info("Write some testss file");
                    RandomFileGenerator.createRandomFile(".txt", 10);
                    RandomFileGenerator.createRandomFile(".xml", 10);
                    RandomFileGenerator.createRandomFile(".pdf", 10);
                    RandomFileGenerator.createRandomFile(".csv", 10);
                }catch (Exception e){
                    LOG.error(e.getMessage(),e);
                }
            });


            AppTrayIcon trayIconBean = context.getBean(AppTrayIcon.class);

            arquivosTeste.start();
            trayIconBean.setupApplication();

        }finally {
            new File(ParameterConfig.CONFIG_PATH).delete();
        }
    }

}