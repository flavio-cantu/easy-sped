package com.cantuaria.updater.ui;

import com.cantuaria.updater.file.DirWatcher;
import com.cantuaria.updater.file.S3Service;
import com.cantuaria.updater.param.ParameterService;
import com.cantuaria.updater.record.NotaFiscalService;
import com.cantuaria.updater.upload.config.ParameterConfig;
import com.cantuaria.updater.upload.config.UploaderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class AppTrayIcon {

    private static final Logger LOG = LoggerFactory.getLogger(AppTrayIcon.class);
    private static TrayIcon trayIcon;

    @Autowired
    private NotaFiscalService uploadService;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private ParameterService parameterService;

    private static DirWatcher dirWatcher;


    public void createTrayIcon() {
        if (!SystemTray.isSupported()) {
            LOG.error("Tray icon não suportado!");
            return;
        }

        // Item para selecionar diretório
        MenuItem selectDirItem = new MenuItem("Selecionar Diretório");
        selectDirItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setDialogTitle("Selecione um diretório");

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedDir = fileChooser.getSelectedFile();
                String monitoringPath = selectedDir.getAbsolutePath();
                dirWatcher.interrupt();

                parameterService.changeWatchDir(monitoringPath);
                UploaderConfig retrive = ParameterConfig.retrive();
                dirWatcher = new DirWatcher(uploadService, retrive, monitoringPath);
                dirWatcher.start();

                showNotification("Alteração de monitoramento!", "Diretório de monitoramento alterado com sucesso!");
            }
        });


        PopupMenu popup = new PopupMenu();
        MenuItem exitItem = new MenuItem("Sair");
        exitItem.addActionListener(e -> System.exit(0));

        popup.add(selectDirItem);
        popup.addSeparator();
        popup.add(exitItem);

        URL imageUrl = AppTrayIcon.class.getResource("/icons/concept.png");

        if (imageUrl == null) {
            try {
                imageUrl = new File("src/main/resources/icons/icon.png").toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        ImageIcon icon = new ImageIcon(imageUrl);
        trayIcon = new TrayIcon(icon.getImage(), "Uploader", popup);
        trayIcon.setImageAutoSize(true);

        try {
            SystemTray.getSystemTray().add(trayIcon);
        } catch (AWTException e) {
            System.err.println("Erro ao adicionar tray icon");
        }
    }

    public static void showNotification(String title, String message) {
        if (trayIcon != null) {
            trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
        } else {
            LOG.info("{}: {}", title, message);
        }
    }

    public void setupApplication() {
        createTrayIcon();
        UploaderConfig retrive = ParameterConfig.retrive();

        System.setProperty("aws.accessKeyId", retrive.getKey());
        System.setProperty("aws.secretAccessKey", retrive.getAcess());

        s3Service.testConnection(retrive);

        String monitoringPath = parameterService.getWatchDir();

        if (monitoringPath == null) {
            monitoringPath = retrive.getFileFolder();
        }

        dirWatcher = new DirWatcher(uploadService, retrive, monitoringPath);
        dirWatcher.start();

        showNotification("Serviço iniciado", "Monitorando diretório...");
    }

}
