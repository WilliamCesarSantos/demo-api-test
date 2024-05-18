Feature: Cliente
  Scenario: Cadastrar com sucesso
    Given cliente não esta presente na base
    When cadastro o cliente
    Then encontro o cliente cadastrado
    And a requisição deve ter status igual a 201

  Scenario: Cadastrar cliente sem nome
    Given cliente não esta presente na base
    When cadastro o cliente sem informar o nome
    Then erro no cadastro 400

  Scenario: Cadastrar cliente sem documento
    Given cliente não esta presente na base
    When cadastro o cliente sem informar o document
    Then erro no cadastro 400

  Scenario: listar cliente ja cadastrado
    Given cliente já existiver cadastrado
    When pesquisar o cliente pelo nome
    Then deve retornar o cliente dentro da lista