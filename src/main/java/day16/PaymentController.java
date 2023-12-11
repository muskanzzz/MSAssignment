Feature: Payment Controller

  Scenario: Create payment successfully
    Given a debit account with ID "123" and currency "USD" exists
    And a credit account with ID "456" and currency "USD" exists
    When the user creates a payment instruction from account "123" to account "456" with amount 100.0
    Then the response status should be OK
    And the response body should contain success message

  Scenario: Create payment with account not found
    Given the debit account with ID "123" does not exist
    And the credit account with ID "456" does not exist
    When the user creates a payment instruction from account "123" to account "456" with amount 100.0
    Then the response status should be BAD REQUEST
    And the response body should contain error message
