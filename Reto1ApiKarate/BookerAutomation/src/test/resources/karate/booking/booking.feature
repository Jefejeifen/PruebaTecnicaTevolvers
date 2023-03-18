#Author: Dylan Mateo Llano Jaramillo
Feature: As a user I want to automate the booker api

  Background:
    * url api.bookingRestful

  @CreateBookingSuccessful
  Scenario Outline: Successfully create a reservation for a user
    * def pathBooking = '/booking'
    * def jsonBody =
      """
      {
        "firstname": "<firstname>",
        "lastname": "<lastname>",
        "totalprice": <totalprice>,
        "depositpaid": "<depositpaid>",
        "bookingdates": {
            "checkin": "<checkin>",
            "checkout": "<checkout>"
        },
        "additionalneeds": "<additionalneeds>"
      }
      """
    Given path pathBooking
    And header Content-Type = '<header>'
    And header Accept = '<header>'
    And request jsonBody
    When method POST
    Then status <statusCode>
    * print response
    And match response ==
    """
          {
          "bookingid": '#number',
          "booking": {
              "firstname": '#string',
              "lastname": '#string',
              "totalprice": '#number',
              "depositpaid": '#notnull',
              "bookingdates": {
                  "checkin": '#notnull',
                  "checkout": '#notnull'
              },
              "additionalneeds": '#string'
          }
      }
    """

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds | statusCode | header           |
      | Jim       | Brown    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 200        | application/json |
      | Dylan     | Llano    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 200        | application/json |

  @CreateBookingAlternative
  Scenario Outline: Alternative Scenary to create a reservation for a user
    * def pathBooking = '/booking'
    * json jsonBody = {"firstname": "<firstname>","lastname": "<lastname>","totalprice": <totalprice>,"depositpaid": <depositpaid>,"bookingdates": {"checkin": "<checkin>","checkout": "<checkout>"},"additionalneeds": "<additionalneeds>"}
    Given path pathBooking
    And header Content-Type = '<ContentTypeHeader>'
    And header Accept = '<AcceptHeader>'
    And request jsonBody
    When method POST
    Then status <statusCode>
    * print response

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds | statusCode | ContentTypeHeader | AcceptHeader     |
      | Jim       |          | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 200        | application/json  | application/json |
      |           | Brown    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 200        | application/json  | application/json |
      | Jim       | Brown    | null       | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 500        | application/json  | application/json |

  @CreateBookingScenaryHeadersError
  Scenario Outline: Alternate scenario where headers are sent incorrectly
    * def pathBooking = '/booking'
    * def jsonBody =
      """
      {
        "firstname": "<firstname>",
        "lastname": "<lastname>",
        "totalprice": <totalprice>,
        "depositpaid": "<depositpaid>",
        "bookingdates": {
            "checkin": "<checkin>",
            "checkout": "<checkout>"
        },
        "additionalneeds": "<additionalneeds>"
      }
      """
    Given path pathBooking
    And header Content-Type = '<ContentTypeHeader>'
    And header Accept = '<AcceptHeader>'
    And request jsonBody
    When method POST
    Then status <statusCode>
    * print response
    And match response == "<responseText>"

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds | statusCode | ContentTypeHeader | AcceptHeader     | responseText          |
      | Jim       | Brown    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 500        |                   |                  | Internal Server Error |
      | Jim       | Brown    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 418        | application/json  |                  | I'm a Teapot          |
      | Jim       | Brown    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 418        | application/json  | sdadsaasdd       | I'm a Teapot          |
      | Jim       | Brown    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 500        |                   | application/json | Internal Server Error |
      | Jim       | Brown    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 500        | dadsadaadad       | application/json | Internal Server Error |

  @CreateBookingScenaryWithoutBookingdates
  Scenario Outline: Alternative scenario where no value is sent to checkin and checkout
    * def pathBooking = '/booking'
    * def jsonBody =
      """
      {
        "firstname": "<firstname>",
        "lastname": "<lastname>",
        "totalprice": <totalprice>,
        "depositpaid": "<depositpaid>",
        "bookingdates": {
            "checkin": "<checkin>",
            "checkout": "<checkout>"
        },
        "additionalneeds": "<additionalneeds>"
      }
      """
    Given path pathBooking
    And header Content-Type = '<ContentTypeHeader>'
    And header Accept = '<AcceptHeader>'
    And request jsonBody
    When method POST
    Then status <statusCode>
    * print response
    And match response.booking.bookingdates.<bookingdates> == '0NaN-aN-aN'

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds | statusCode | ContentTypeHeader | AcceptHeader     | bookingdates |
      | Jim       | Brown    | 111        | true        | 2019-01-01 |            | Breakfast       | 200        | application/json  | application/json | checkout     |
      | Jim       | Brown    | 111        | true        |            | 2019-01-01 | Breakfast       | 200        | application/json  | application/json | checkin      |

  @GetBookingSuccessful
  Scenario Outline: Validate by obtaining an existing booking ID and validate that the correct booking information is returned.
    * def pathBooking = '/booking/' + <userId>
    Given path pathBooking
    And header Accept = '<header>'
    When method GET
    Then status <statusCode>
    * print response
    * def expected = {"firstname": "#string","lastname": "#string","totalprice": #number,"depositpaid": #notnull,"bookingdates": {"checkin": "#string","checkout": "#string"}}
    * if (response.hasOwnProperty("additionalneeds")) expected.additionalneeds = "#string"
    And match response == expected
    * karate.match(response, expected)

    Examples:
      | userId | header           | statusCode |
      | 1      | application/json | 200        |
      | 2      | application/json | 200        |
      | 3      | application/json | 200        |

  @GetBookingAlternativeIdNotFound
  Scenario Outline: Validate a booking ID that does not exist and validate that a 404 error is returned
    * def pathBooking = '/booking/' + '<userId>'
    Given path pathBooking
    And header Accept = '<header>'
    When method GET
    Then status <statusCode>
    * print response
    And match response == '<responseMessage>'

    Examples:
      | statusCode | header           | userId   | responseMessage |
      | 404        | application/json | 487890   | Not Found       |
      | 404        | application/json | 111111   | Not Found       |
      | 404        | application/json | 55433    | Not Found       |
      | 404        | application/json | no_found | Not Found       |

  @UpdateBookingSuccessful
  Scenario Outline: Validate booking update successfully
    * def pathBooking = '/booking/' + '<userId>'
    * def accessToken = karate.callSingle('../booking/createToken.feature').token
    * print accessToken
    * def jsonBody =
      """
      {
        "firstname": "<firstname>",
        "lastname": "<lastname>",
        "totalprice": <totalprice>,
        "depositpaid": "<depositpaid>",
        "bookingdates": {
            "checkin": "<checkin>",
            "checkout": "<checkout>"
        },
        "additionalneeds": "<additionalneeds>"
      }
      """
    Given path pathBooking
    And header Content-Type = '<header>'
    And header Accept = '<header>'
    And header Cookie = 'token=' + accessToken
    And request jsonBody
    When method PUT
    Then status <statusCode>
    * print response
    And match response ==
    """
        {
            "firstname": "#string",
            "lastname": "#string",
            "totalprice": #number,
            "depositpaid": #notnull,
            "bookingdates": {
                "checkin": "#string",
                "checkout": "#string"
            },
            "additionalneeds": "#string"
        }
    """

    Examples:
      | userId | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds | statusCode | header           |
      | 1      | Jim       | Brown    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 200        | application/json |

  @UpdateBookingFailed
  Scenario Outline: Validate booking update failed
    * def pathBooking = '/booking/' + '<userId>'
    * def accessToken = karate.callSingle('../booking/createToken.feature').token
    * def jsonBody =
      """
      {
        "firstname": "<firstname>",
        "lastname": "<lastname>",
        "totalprice": <totalprice>,
        "depositpaid": "<depositpaid>",
        "bookingdates": {
            "checkin": "<checkin>",
            "checkout": "<checkout>"
        },
        "additionalneeds": "<additionalneeds>"
      }
      """
    Given path pathBooking
    And header Content-Type = '<header>'
    And header Accept = '<header>'
    And header Cookie = 'token=' + ''
    And request jsonBody
    When method PUT
    Then status <statusCode>
    * print response
    And match response == 'Forbidden'
    Examples:
      | userId | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds | statusCode | header           |
      | 1      | Jim       | Brown    | 111        | true        | 2019-01-01 | 2019-01-01 | Breakfast       | 403        | application/json |
