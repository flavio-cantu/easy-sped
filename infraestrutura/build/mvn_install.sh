#!/bin/bash

# Verifica se os parâmetros necessários foram fornecidos
if [ -z "$1" ] ; then
  echo "Erro: Parâmetros insuficientes."
  echo "Uso: $0 <diretorio-do-projeto>"
  echo "Exemplo: $0 /caminho/para/projeto"
  exit 1
fi

PROJECT_DIR=$1  # Diretório do projeto recebido como parâmetro
CONTAINER_NAME="maven-builder-$(date +%s)"

# Validações iniciais
if [ ! -d "$PROJECT_DIR" ]; then
  echo "Erro: Diretório do projeto não encontrado: $PROJECT_DIR"
  exit 1
fi


echo "Iniciando processo de instalação..."

# Executa o container Maven para fazer a build
echo "Executando build Maven no container Docker..."
docker run --rm \
  --name "$CONTAINER_NAME" \
  -v "$PROJECT_DIR":/app \
  -v "$(pwd)/../m2":/root/.m2 \
  -w /app \
  jelastic/maven:3.9.5-openjdk-21 \
  mvn clean install clean -DskipTests

# Verifica se a build foi bem-sucedida
if [ $? -ne 0 ]; then
  echo "Erro durante a build do projeto Maven."
  exit 1
fi

echo "Instalação concluída com sucesso!"


exit 0