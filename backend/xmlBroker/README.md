# LEIA-ME com atenção!!!

### Reference Documentation

For further reference, please consider the following sections:

* [Spring Boot](https://docs.spring.io/spring-boot/3.4.5)
* [AWS S3 Bucket](https://docs.aws.amazon.com/AmazonS3/latest/userguide/Welcome.html)
* [AWS SQS](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/welcome.html)
* [AWS IAM](https://docs.aws.amazon.com/AmazonS3/latest/userguide/security-iam.html)

## Objetivo

Este projeto tem como objetivo processar documentos que foram recebidos via S3 Bucket.

### Como funciona?

Processamento de nota fiscal é parametrizado.
As notas fiscais mudam de acordo com o órgão emissor (município ou UF). Por isso existe uma tabela parâmetro:
`CLP_CLIENTE_NF_LAYOUT_PARAM` que fará o relacionamento de CNPJ e processador.

É excencial para o funcionamento encontrar o CNPJ no xml. Lógica que deve ser implementada aqui:
`XmlProcessorService.parseXml`.
Em seguida o sistema vai na tabela de parâmetro consultar qual o processador para aquele CNPJ.

Layout's existentes:

* `com.cantuaria.broker.processor.layout.PostoCombustivelCidadeOcidentalGO`

Essa lógica tende a ser a maior parte do projeto. Dessa forma evitamos erros ao importar os arquivos.
Também temos como fazer testes automatizados para garantir a qualidade dos dados.
O trabalho nessa entrada de dados dá a solução a performace necessária no produto final.

## Segurança

Este serviço é executado dentro do ecossistema AWS por isso as permissões
estarão atreladas a IAM vinculado a instância EC2.

## Perfís

Nesta aplicação, os perfís disponíveis são:

* `local` - Habilita o stub das fronteiras com AWS
* `aws` - Habilita conexão real com AWS
* `h2` - Banco de dados em memória para desenvolvimento
* `db` - Banco de dados PostgreSQL hosteado pela AWS.
  O banco não é acessível fora da núvem por questões de segurança.

## Modo de DESENVOLVIMENTO

Para acelerar a mantutenção este módulo conta com uma camada local para a aplicação.
Nela, é possível desenvolver e testar as funcionlidades da aplicação sem gerar custos de núvem.

Para isso execute a classe:
`
AppXmlBrokerLocal
`

Ela já irá preparar o ambiente com a cloud stubada para poder desenvolver tranquilamente
local.

Nesta aplicação, o arquivo `resources/bucket/xml-formatado.xml` estará disponível no "S3 Stub"
e já existirá uma mensagem para processar esse arquivo.
