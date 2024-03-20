# language: pt
Funcionalidade: API - Relatórios

  Cenario: Visualizar relatório
    Dado que as horas de trabalho do funcionario já existam
    Quando requisitar o relatorio de horas de trabalho do funcionario
    Entao o relatorio é gerado com sucesso
