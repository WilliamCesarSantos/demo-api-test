Feature: Product

  Scenario: Register a new product
    Given product is new
    When I post product
    Then product was registered
    And response should status equals 201

  Scenario: Search product already registered
    Given product already registered
    When I search for barcode
    Then product with barcode was found
    And response should status equals 200