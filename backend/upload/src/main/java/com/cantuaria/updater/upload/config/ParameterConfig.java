package com.cantuaria.updater.upload.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ParameterConfig {

    public static final String CONFIG_PATH = "./upload.config";

    public static UploaderConfig retrive() {
        try(FileInputStream fileInputStream = new FileInputStream(CONFIG_PATH)) {
            try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                return (UploaderConfig) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
