package com.cantuaria.updater.file;

import com.cantuaria.updater.record.NotaFiscalService;
import com.cantuaria.updater.ui.AppTrayIcon;
import com.cantuaria.updater.upload.config.UploaderConfig;
import com.cantuaria.updater.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;


public class DirWatcher extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(DirWatcher.class);

    private final NotaFiscalService uploadService;
    private final UploaderConfig config;
    private final String monitoringPath;
    private boolean ativo;


    public DirWatcher(NotaFiscalService uploadService, UploaderConfig config, String monitoringPath){
        this.uploadService = uploadService;
        this.monitoringPath = monitoringPath;
        this.config = config;
        this.ativo = true;
    }

    @Override
    public void run() {
        LOG.info("Iniciando monitoramento do diretório: {}", monitoringPath);
        Path watchDir = Paths.get(monitoringPath);
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            watchDir.register(watcher, ENTRY_CREATE);

            while (ativo) {
                WatchKey key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_CREATE) {
                        Path newFile = watchDir.resolve((Path) event.context());

                        if (!hasAllowedExtension(newFile)) {
                            break;
                        }
                        if (exceedsMaxSize(newFile)) {
                            break;
                        }

                        int count = 0;
                        while (count < 100) {
                            try {
                                String content = Files.readString(newFile, StandardCharsets.UTF_8);
                                String md5 = MD5Util.calculateMd5(content);
                                if (isNewFile(md5)) {
                                    uploadService.registerUpload(md5, newFile);
                                } else {
                                    AppTrayIcon.showNotification("Alerta!", "Arquivo com conteúdo idêntico enviado");
                                }
                                break;
                            } catch (FileSystemException e) {
                                Thread.sleep(1000);
                                count++;
                                LOG.warn(e.getMessage());
                            }
                        }
                    }
                }
                key.reset();
            }
        } catch (Exception e) {
            AppTrayIcon.showNotification("Erro", "Falha no monitoramento");
            LOG.error(e.getMessage(), e.getCause());
        }
    }

    private boolean exceedsMaxSize(Path filePath) throws IOException {
        long maxBytes = parseSize(config.getMaxFileSize());
        long fileSize = Files.size(filePath);
        return fileSize > maxBytes;
    }

    private boolean hasAllowedExtension(Path filePath) {
        String fileName = filePath.toString().toLowerCase();
        return config.getAllowedExtensions().stream()
                .anyMatch(ext -> fileName.endsWith(ext.toLowerCase()));
    }

    private boolean isNewFile(String md5) throws IOException {
        return !uploadService.hasThisFile(md5);
    }

    private long parseSize(String size) {
        size = size.toUpperCase();
        if (size.endsWith("KB")) {
            return Long.parseLong(size.replace("KB", "")) * 1024;
        } else if (size.endsWith("MB")) {
            return Long.parseLong(size.replace("MB", "")) * 1024 * 1024;
        } else if (size.endsWith("GB")) {
            return Long.parseLong(size.replace("GB", "")) * 1024 * 1024 * 1024;
        }
        return Long.parseLong(size);
    }

    @Override
    public void interrupt() {
        this.ativo = false;
        super.interrupt();
    }
}