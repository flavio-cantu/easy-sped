#!/bin/bash

# Verifica se os parâmetros necessários foram fornecidos
if [ -z "$1" ] || [ -z "$2" ] || [ -z "$3" ]; then
  echo "Erro: Parâmetros insuficientes."
  echo "Uso: $0 <nome-do-bucket> <caminho-do-bucket> <diretorio-kubernetes>"
  echo "Exemplo: $0 meu-bucket-s3 config/backend /caminho/para/microk8s"
  exit 1
fi

BUCKET_NAME=$1
S3_TARGET_PATH=$2
KUBE_DIR=$3


# Validações iniciais
if [ ! -d "$KUBE_DIR" ]; then
  echo "Erro: Diretório Kubernetes não encontrado: $KUBE_DIR"
  exit 1
fi

echo "Iniciando upload de arquivos Kubernetes..."
echo "Bucket S3 de destino: $BUCKET_NAME"
echo "Diretório local: $KUBE_DIR"
echo "Pasta no S3: $S3_TARGET_PATH"

# Contador de arquivos processados
COUNTER=0

# Processa cada arquivo YAML/JSON no diretório
for FILE in "$KUBE_DIR"/*.{yaml,yml,json}; do
  if [ -f "$FILE" ]; then
    echo "Enviando arquivo: $(basename "$FILE")"
    aws s3 cp "$FILE" "s3://$BUCKET_NAME/app/$S3_TARGET_PATH/$(basename "$FILE")"
    
    if [ $? -eq 0 ]; then
      echo "Sucesso: $(basename "$FILE")"
      ((COUNTER++))
    else
      echo "Erro ao enviar: $(basename "$FILE")"
    fi
  fi
done

# Resultado final
echo "Upload concluído!"
echo "Total de arquivos enviados: $COUNTER"
echo "Os arquivos estão disponíveis em: s3://$BUCKET_NAME/$S3_TARGET_PATH/"

exit 0