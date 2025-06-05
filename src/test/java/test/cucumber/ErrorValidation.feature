@tag
  Feature: Error validation

    @ErrorValidation
    Scenario Outline:
      Given I landed on Ecommerce Page
      When Logged in with username <username> and password <password>
      Then "Incorrect or password." message is displayed in LoginPage inisde a Toast
      Examples:
        | username         | password |
        | nishal@gmail.com | 123456Aa|