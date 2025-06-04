# LEIA-ME com atenção!!!

### Reference Documentation

For further reference, please consider the following sections:

* [Spring Boot](https://docs.spring.io/spring-boot/3.4.5)

## Objetivo

Este projeto tem como objetivo servir funcionalidades para criação e manipulação da escrituração

### Como funciona?

De fomra simplificada, utilizamos da base de dados comum para fazer o parse e as validações
da escrituração.

## Segurança

Este serviço é executado dentro do ecossistema AWS por isso as permissões
estarão atreladas a IAM vinculado a instância EC2.

## Perfís

Nesta aplicação, os perfís disponíveis são:

* `local` - Habilita o stub das fronteiras com AWS
* `h2` - Banco de dados em memória para desenvolvimento
* `db` - Banco de dados PostgreSQL hosteado pela AWS.
  O banco não é acessível fora da núvem por questões de segurança.

## Modo de DESENVOLVIMENTO

Para acelerar a mantutenção este módulo conta com uma camada local para a aplicação.
Nela, é possível desenvolver e testar as funcionlidades da aplicação sem gerar custos de núvem.

Para isso execute a classe:
`
AppBookkeepingLocal
`


//TODO add lombok