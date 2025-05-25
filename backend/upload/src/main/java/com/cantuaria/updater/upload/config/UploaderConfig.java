package com.cantuaria.updater.upload.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UploaderConfig implements Serializable {

    private List<String> allowedExtensions;
    private String maxFileSize;

    private String key;
    private String acess;
    private String region;

    private String bucketName;
    private String clientFolder;

    private String fileFolder;

    public UploaderConfig(String key, String acess, String region, String bucketName, String clientFolder, String fileFolder) {
        this.maxFileSize = "5MB";
        allowedExtensions = Arrays.asList(
                ".xml", ".pdf"
        );
        this.key = key;
        this.acess = acess;
        this.region = region;
        this.bucketName = bucketName;
        this.clientFolder = clientFolder;
        this.fileFolder = fileFolder;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getKey() {
        return key;
    }

    public String getAcess() {
        return acess;
    }

    public String getClientFolder() {
        return clientFolder;
    }

    public List<String> getAllowedExtensions() {
        return allowedExtensions;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public String getFileFolder() {
        return fileFolder;
    }

    public String getRegion() {
        return region;
    }
}
