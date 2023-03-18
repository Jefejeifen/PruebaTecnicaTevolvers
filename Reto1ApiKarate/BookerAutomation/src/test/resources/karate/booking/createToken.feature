Feature: I as a user would like to generate a token to update the booking.

  Background:
    * url api.bookingRestful

  Scenario Outline: Generate Token
    * def pathBooking = '/auth'
    * json jsonBody = {"username": "admin","password": "password123"}
    Given path pathBooking
    And header Content-Type = "<header>"
    And request jsonBody
    When method POST
    Then status <statusCode>
    * print response
    * def token = response.token
    * print token

    Examples:
      | header           | statusCode |
      | application/json | 200        |