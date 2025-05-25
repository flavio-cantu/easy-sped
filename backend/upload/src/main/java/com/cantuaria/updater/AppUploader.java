package com.cantuaria.updater;

import com.cantuaria.updater.upload.config.ParameterConfig;
import com.cantuaria.updater.upload.config.UploaderConfig;
import com.cantuaria.updater.upload.config.UploaderConfigGenerator;
import com.cantuaria.updater.ui.AppTrayIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.swing.*;

@SpringBootApplication
public class AppUploader {
    private static final Logger LOG = LoggerFactory.getLogger(AppUploader.class);

    public static void main(String[] args) {
        LOG.info("Starting with :{}", Arrays.toString(args));

        // Modo configuração
        if (args.length > 0 && (args[0].equals("--configure")
                || args[0].equals("-c")
                || args[0].equals("-h")
                || args[0].equals("--help"))) {
            UploaderConfigGenerator.generateFromArgs(Arrays.copyOfRange(args, 1, args.length));
            return;
        }

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        UploaderConfig parameter = ParameterConfig.retrive();
        System.setProperty("aws.region", parameter.getRegion());

        ConfigurableApplicationContext context = new SpringApplicationBuilder(AppUploader.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        AppTrayIcon trayIconBean = context.getBean(AppTrayIcon.class);
        trayIconBean.setupApplication();
    }


}
