#!/bin/bash

CURRENT_PATH=$(pwd)
# Inicia o container PostgreSQL e captura o ID
CONTAINER_ID=$(docker run -d --name postgres \
  -e POSTGRES_PASSWORD=senha123 \
  -e POSTGRES_DB=meubanco \
  -p 5432:5432 \
  -e POSTGRES_CONFIG_ARGS="-c listen_addresses='*'" \
  -v "$(pwd):/schemaspy" \
  postgres:latest)

echo "Container PostgreSQL iniciado com ID: $CONTAINER_ID"

# Aguarda o PostgreSQL estar pronto para aceitar conexões
echo "Aguardando PostgreSQL inicializar..."
sleep 10  # Ajuste este tempo conforme necessário

cd ..
# Executa a aplicação Maven
#mvn exec:java -Dexec.mainClass="com.cantuaria.AppCreatingMer"
mvn test -Dtest=com.cantuaria.AppCreatingMer

cd $CURRENT_PATH
# Executa o SchemaSpy
java -jar schemaspy-6.2.4.jar -configFile schemaspy.properties

# Para e remove o container
docker stop $CONTAINER_ID
docker rm $CONTAINER_ID
echo "Container PostgreSQL removido"