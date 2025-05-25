# LEIA-ME com atenção!!!

### Reference Documentation

For further reference, please consider the following sections:

* [Spring Boot](https://docs.spring.io/spring-boot/3.4.5)
* [AWS S3 Bucket](https://docs.aws.amazon.com/AmazonS3/latest/userguide/Welcome.html)
* [AWS IAM](https://docs.aws.amazon.com/AmazonS3/latest/userguide/security-iam.html)

### Objetivo

Este projeto tem como objetivo enviar arquivos para um ambiente AWS usando S3 Bucket do tipo:

* XML
* PDF

E arquivos de no máximo 5MB.

### Segurança

Por se tratar de um aplicativo externo ao controle de segurança do servidor,
os acessos foram restringidos ao mínimo.

Ou seja:

1) Os cliente deve estar cadastrados no IAM para objetr a chave de acesso a núvem.
2) A empresa deve fornecer junto a este APP um arquivo de nome `upload.config`.
   Neste arquivo todos os dados importantes de acesso estão convertidos em formato apenas
   levigel pelo sistema.
3) É obrigação do cliente proteger esse arquivo e como sugestão deixa-lo oculto na pasta do App.

Esse arquivo contém as credenciais do cliente que permitem que ela envie os arquivos
para a núvem da empresa. Regerar é simples, mas dependendo da quantidade de estabelicimentos
da empresa, pode se tornar um incoveniente.

Por outro lado, do lado da núvem tudo será monitorado e podemos incluvive restringir IP's e
tomar algumas decisões de segurança do lado do servidor para evitar problemas.

### Modo de DESENVOLVIMENTO

Para acelerar a mantutenção este módulo conta com uma camada local par a aplicação.
Nela, é possível desenvolver e testar as funcionlidades da aplicação sem gerar custos de núvem.

Para isso execute a classe:
`
AppUploaderLocal
`

Ela já irá preparar o ambiente com a cloud stubada para poder desenvolver tranquilamente
local.
