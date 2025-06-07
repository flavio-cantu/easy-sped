# language: pt
Funcionalidade: Escrituração - Consolidação

  Contexto:
    Dado que exista a seguinte solicitação de escrituração:
      | Cliente     | Inicio     | Fim        |
      | Posto Shell | 01/06/2025 | 30/06/2025 |


  Cenario: Consolidando uma escrituração
    Dado que o usuário solicite ao sistema a consolidação da escrituração do cliente 'Posto Shell'
    Quando sistema receber a solicitação de consolidação
    Então o sistema deverá marcar escrituração como 'Pendente'
    E a irá iniciar um evento assíncrono de nome 'Consolidar escrituração'

  Cenario: Processando consolidação
    Dado que exista uma solicitação com status de 'Pendente'
    Quando o sistema processar a consolidação
    Então a escrituração estará gerada e validada no sistema
