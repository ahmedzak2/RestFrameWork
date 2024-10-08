Feature: Validate Place API

  Scenario: Verify if a customer can add a place using Add Place API
    Given add place payload
    When user calls "/maps/api/place/add/json" with http request
    Then The API request is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
