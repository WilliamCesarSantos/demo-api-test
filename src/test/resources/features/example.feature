Feature: Cadastro de cliente
  Scenario: Cadastrar com sucesso
    Given cliente não esta presente na base
    When cadastro o cliente
    Then encontro o cliente cadastrado

  Scenario: Cadastrar cliente sem nome
    Given cliente não esta presente na base
    When cadastro o cliente sem informar o nome
    Then erro no cadastro 400