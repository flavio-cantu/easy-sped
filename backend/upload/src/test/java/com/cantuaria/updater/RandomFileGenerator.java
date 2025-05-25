package com.cantuaria.updater;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

public class RandomFileGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    public static Path createRandomFile(String extension, int contentLength) throws IOException {
        // Gera nome único para o arquivo
        String fileName = "target/file_" + UUID.randomUUID().toString().substring(0, 8) + extension;

        // Gera conteúdo aleatório
        String content = generateRandomContent(contentLength);

        // Cria o arquivo
        Path filePath = Paths.get(fileName);
        Files.write(filePath, content.getBytes());

        return filePath;
    }

    private static String generateRandomContent(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            // Exemplo de uso: cria arquivo .txt com 100 caracteres
            Path file = createRandomFile(".txt", 100);
            System.out.println("Arquivo criado: " + file.toAbsolutePath());
            System.out.println("Conteúdo: " + Files.readString(file));
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo: " + e.getMessage());
        }
    }
}
