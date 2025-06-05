#!/bin/bash

# Script para subir ActiveMQ no Docker

CONTAINER_NAME="activemq"
ACTIVEMQ_VERSION="5.17.7"
PORT_WEB="8161"
PORT_TCP="61616"

echo "Iniciando container ActiveMQ..."

docker run -it --rm \
  --name $CONTAINER_NAME \
  -p $PORT_WEB:8161 \
  -p $PORT_TCP:61616 \
  -e ACTIVEMQ_ADMIN_LOGIN=admin \
  -e ACTIVEMQ_ADMIN_PASSWORD=admin \
  apache/activemq-classic:$ACTIVEMQ_VERSION
