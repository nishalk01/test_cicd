@tag
Feature: Purchase the order from Ecommerce Website
  Background:
    Given I landed on Ecommerce Page
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add the product <productName> to Cart
    And Checkout <productName> and Submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage
    Examples:
      | username         | password | productName |
      | nishal@gmail.com | 123456@Aa| ZARA COAT 3 |