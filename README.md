# LEIA-ME com atenção!!!

## Reference Documentation

Tecnologias principais utilizadas

* [Spring Boot](https://docs.spring.io/spring-boot/3.4.5)
* [AWS](https://docs.aws.amazon.com)
* [Terraform](https://terraform.io/)
* [Docker](https://www.docker.com/)

## Objetivo

Instrumentar o ambiente de projeto de conceito com instruções que sejam executáveis.
Essa estrutura é a apenas um projeto de presentação e demonstração de conhecimento. Não tem estrutura/segurança para ser
executado em produção.

## Etapas

É esperado que o ambiente de execução possua:

* Terraform configurado
* Docker configurado
* AwsCLI configurado

### Infraestrutura

1) aws_env: `terraform apply -auto-approve`
    * Toda a infra estrutura para executar o projeto. Cuidado que a máquina provisionada para poc não é barata. Não a
      deixe ligada por muitas horas.
    * Aqui vai ter como output o nome do bucket

2) customer_env:
    * Aqui vamos credenciar um posto na aws para o envio dos arquivos.
    * Ajuste o nome do bucket
    * Cria um workspace. Ex: postoA.
    * `terraform apply -auto-approve`
    * Como output temos as credenciais do usuário
    * Ao final teremos no bucket um arquivo de configuração pronto pra uso.
      Só baixar e colocar ao lado do upload.jar e iniciar o programa
