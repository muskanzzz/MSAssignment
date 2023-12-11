Feature: Account Controller

  Scenario: Get account details when the account exists
    Given an account with ID "123"
    When the user requests account details
    Then the response status should be OK
    And the response body should contain account details

  Scenario: Get account details when the account does not exist
    Given an account with ID "456" does not exist
    When the user requests account details
    Then the response status should be NOT FOUND
    And the response body should be empty

  Scenario: Get account by payment method when the account exists
    Given an account with a payment method "credit-card"
    When the user requests account by payment method
    Then the response status should be OK
    And the response body should contain account details

  Scenario: Get account by payment method when the account does not exist
    Given an account with a payment method "paypal" does not exist
    When the user requests account by payment method
    Then the response status should be NOT FOUND
    And the response body should be empty
