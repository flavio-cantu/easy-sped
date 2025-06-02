#!/bin/bash

# Verifica se os parâmetros necessários foram fornecidos
if [ -z "$1" ] || [ -z "$2" ] || [ -z "$3" ]|| [ -z "$4" ]; then
  echo "Erro: Parâmetros insuficientes."
  echo "Uso: $0 <aws-key> <aws-access> <aws-region> <bucket-name> <bucket-folder>"
  echo "Exemplo: $0 AKIA abc123 sa-east-1 meu-bucket postoAlberto"
  exit 1
fi


KEY=$1
ACCESS=$2
REGION=$3
BUCKET_NAME=$4
BUCKET_FOLDER=$5

JAR_PATH=target/cloud-updater-*.jar
PROJECT_DIR="$(pwd)/../../backend/upload"
CONTAINER_NAME="maven-builder-$(date +%s)"

# Validações iniciais
if [ ! -d "$PROJECT_DIR" ]; then
  echo "Erro: Diretório do projeto não encontrado: $PROJECT_DIR"
  exit 1
fi



echo "Iniciando processo de build e upload..."

# Executa o container Maven para fazer a build
echo "Executando build Maven no container Docker..."
docker run --rm \
  --name "$CONTAINER_NAME" \
  -v "$PROJECT_DIR":/app \
  -v "$(pwd)/../m2":/root/.m2 \
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
JAR_NAME=$(basename "$JAR_FILE")


docker run --rm \
  --name "$CONTAINER_NAME" \
  -v "$PROJECT_DIR/target":/target \
  -w /target \
  jelastic/maven:3.9.5-openjdk-21 \
  java -jar $JAR_NAME --configure --key=$KEY --access=$ACCESS --region=$REGION --bucket=$BUCKET_NAME --folder=$BUCKET_FOLDER --watchFolder=.


# Faz upload para o S3
echo "Iniciando upload para S3..."
aws s3 cp "$PROJECT_DIR/target/upload.config" "s3://$BUCKET_NAME/credential/$BUCKET_FOLDER/upload.config"

if [ $? -eq 0 ]; then
  echo "Upload concluído com sucesso!"
  echo "Disponível em: s3://$BUCKET_NAME/credential/$BUCKET_FOLDER/upload.config"
else
  echo "Erro durante o upload para S3(s3://$BUCKET_NAME/credential/$BUCKET_FOLDER)"
  exit 1
fi

docker run --rm \
  --name "$CONTAINER_NAME" \
  -v "$PROJECT_DIR":/app \
  -v "$HOME/.m2":/root/.m2 \
  -w /app \
  jelastic/maven:3.9.5-openjdk-21 \
  mvn clean

exit 0