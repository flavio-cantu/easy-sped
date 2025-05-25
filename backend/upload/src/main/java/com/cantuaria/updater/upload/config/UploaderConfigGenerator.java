package com.cantuaria.updater.upload.config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UploaderConfigGenerator {

    public static void generateFromArgs(String[] args) {
        Map<String, String> params = parseArguments(args);

        if (params.containsKey("help") || args.length == 0) {
            printHelp();
            return;
        }

        validateParams(params);

        UploaderConfig uploadConfig = new UploaderConfig(
                params.get("key"),
                params.get("access"),
                params.get("region"),
                params.get("bucket"),
                params.get("folder"),
                params.get("watchfolder")
        );

        saveConfig(uploadConfig);
        System.out.println("Configuração salva com sucesso!");
    }

    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> params = new HashMap<>();

        for (String arg : args) {
            if (arg.equals("-h") || arg.equals("--help")) {
                params.put("help", "true");
                continue;
            }

            if (arg.startsWith("--")) {
                String[] parts = arg.substring(2).split("=", 2);
                if (parts.length == 2) {
                    params.put(parts[0].toLowerCase(), parts[1]);
                }
            }
        }

        return params;
    }

    private static void validateParams(Map<String, String> params) {
        if (!params.containsKey("key")
                || !params.containsKey("access")
                || !params.containsKey("region")
                || !params.containsKey("bucket")
                || !params.containsKey("folder")
                || !params.containsKey("watchfolder")) {
            System.err.println("Erro: Parâmetros obrigatórios faltando");
            printHelp();
            System.exit(1);
        }
    }

    private static void printHelp() {
        String helpText = """
        Uso: java -jar upload.jar [OPÇÕES]
        
        Opções:
          -h, --help              Mostra esta ajuda
          --key=CHAVE             Define a chave de acesso AWS
          --access=SECRET         Define o segredo de acesso AWS
          --region=REGION         Define a região base de envio dos arquivos
          --bucket=NOME_BUCKET    Define o nome do bucket S3
          --folder=client-folder  Define a pasta no bucket que o cliente tem permissão
          --watchFolder=.         Define a pasta na máquina que será observada a chegada de arquivos
        
        Exemplo:
          java -jar upload.jar --key=AKIA... --access=abc123... --region=sa-east-1  --bucket=meu-bucket --folder=client-folder --watchFolder=.
          
        Para configuração persistente:
          java -jar upload.jar --configure --key=AKIA... --access=abc123... --region=sa-east-1 --bucket=meu-bucket --folder=client-folder --watchFolder=.
        """;

        System.out.println(helpText);
    }

    private static void saveConfig(UploaderConfig config) {
        new File(ParameterConfig.CONFIG_PATH).delete();
        try (FileOutputStream fos = new FileOutputStream(ParameterConfig.CONFIG_PATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(config);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar configuração", e);
        }
    }
}