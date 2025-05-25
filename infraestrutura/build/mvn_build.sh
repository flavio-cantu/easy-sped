#!/bin/bash

# Verifica se os parâmetros necessários foram fornecidos
if [ -z "$1" ] || [ -z "$2" ] || [ -z "$3" ]|| [ -z "$4" ]; then
  echo "Erro: Parâmetros insuficientes."
  echo "Uso: $0 <nome-do-bucket> <bucket-path> <caminho-relativo-do-jar> <diretorio-do-projeto>"
  echo "Exemplo: $0 meu-bucket-s3 xmlBroker target/*.jar /caminho/para/projeto"
  exit 1
fi

BUCKET_NAME=$1
BUCKET_PATH=$2
JAR_PATH=$3  # Agora recebido como parâmetro
PROJECT_DIR=$4  # Diretório do projeto recebido como parâmetro
CONTAINER_NAME="maven-builder-$(date +%s)"
LOCAL_OUTPUT_DIR="./build-output"   # Diretório local para cópia temporária

# Validações iniciais
if [ ! -d "$PROJECT_DIR" ]; then
  echo "Erro: Diretório do projeto não encontrado: $PROJECT_DIR"
  exit 1
fi

# Cria diretório de output se não existir
mkdir -p "$LOCAL_OUTPUT_DIR"

echo "Iniciando processo de build e upload..."
echo "Bucket S3 de destino: $BUCKET_NAME"
echo "Diretório do projeto: $PROJECT_DIR"
echo "Padrão do arquivo JAR: $JAR_PATH"

# Executa o container Maven para fazer a build
echo "Executando build Maven no container Docker..."
docker run --rm \
  --name "$CONTAINER_NAME" \
  -v "$PROJECT_DIR":/app \
  -v "/workspace/concept-project/m2":/root/.m2 \
  -w /app \
  jelastic/maven:3.9.5-openjdk-21 \
  mvn clean package -DskipTests

# Verifica se a build foi bem-sucedida
if [ $? -ne 0 ]; then
  echo "Erro durante a build do projeto Maven."
  exit 1
fi

echo "Build concluída com sucesso!"

# Encontra o arquivo JAR no diretório do projeto
FULL_JAR_PATH="$PROJECT_DIR/$JAR_PATH"
JAR_FILE=$(ls $FULL_JAR_PATH 2>/dev/null | head -n 1)

if [ -z "$JAR_FILE" ]; then
  echo "Erro: Não foi possível encontrar o arquivo JAR em: $FULL_JAR_PATH"
  exit 1
fi

echo "Arquivo JAR encontrado: $JAR_FILE"

# Faz upload para o S3
echo "Iniciando upload para S3..."
aws s3 cp "$JAR_FILE" "s3://$BUCKET_NAME/app/$BUCKET_PATH"

if [ $? -eq 0 ]; then
  echo "Upload concluído com sucesso!"
  echo "Disponível em: s3://$BUCKET_NAME/app/$BUCKET_PATH"
else
  echo "Erro durante o upload para S3."
  exit 1
fi

docker run --rm \
  --name "$CONTAINER_NAME" \
  -v "$PROJECT_DIR":/app \
  -w /app \
  jelastic/maven:3.9.5-openjdk-21 \
  mvn clean

exit 0