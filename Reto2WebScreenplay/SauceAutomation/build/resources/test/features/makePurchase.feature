#Author: Dylan Mateo Llano Jaramillo
Feature: As a user, I need to authenticate myself in the saucedemo.com site to make the purchase process.

  @MakePurchaseSuccessful
  Scenario: Generate purchase
    Given the user enters the home page
    When the user tries to authenticate with his credentials
      | standard_user |
      | secret_sauce  |
    And the user to organize the prices from lowest to highest
    And the user chooses the products to the shopping cart
    And the user goes to the shopping cart and removes a product
    And the user enters all the purchase data
      | firstName | lastName | zip   |
      | Dylan     | Llano    | 54321 |
    Then the user validates the total items
    And the user validates the total price
    And user validates purchase success total with a message Thank you for your order!

  @FailedLogin
  Scenario Outline: User does not login successfully
    Given the user enters the home page
    When the user tries to authenticate with his credentials
      | <user>     |
      | <password> |
    Then the user displays the error message <errorMessage>

    Examples:
      | user            | password     | errorMessage                                                              |
      | standard_user   | bad_error    | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      |                 | secret_sauce | Epic sadface: Username is required                                        |
      | locked_out_user |              | Epic sadface: Password is required|

  @LogOutPage
  Scenario: The user logs out of the page
    Given the user enters the home page
    When the user tries to authenticate with his credentials
      | standard_user |
      | secret_sauce  |
    And the user logs out of the page
    Then the user validates the closed session
