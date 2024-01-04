# language: pt
Funcionalidade: API - Produtos

  Cenario: Registrar um novo produto
    Quando submeter um novo produto
    Entao a mensagem é registrada com sucesso

  Cenario: Buscar um produto existente
    Dado que um produto já foi cadastrado
    Quando requisitar a busca de um produto por id
    Entao a mensagem é exibida com sucesso

  Cenario: Alterar um produto existente
    Dado que um produto já foi cadastrado
    Quando requisitar a alteração do produto
    Entao a mensagem é atualizada com sucesso

  Cenario: Excluir um produto existente
    Dado que um produto já foi cadastrado
    Quando requisitar a exclusao do produto
    Entao a mensagem é excluída com sucesso

  Cenario: Listar produtos por categoria
    Dado que um produto já foi cadastrado
    Quando requisitar a listagem de produtos por categoria
    Entao as mensagens são exibidas com sucesso

  Cenario: Listar produtos por ids
    Dado que dois produtos já foram cadastrados
    Quando requisitar a listagem de produtos por ids
    Entao as mensagens são exibidas com sucesso
