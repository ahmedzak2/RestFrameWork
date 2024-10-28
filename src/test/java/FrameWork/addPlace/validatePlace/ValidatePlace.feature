Feature: Validate Place API

  Scenario Outline: Verify if a customer can add a place using Add Place API
    Given add place PayLoad with "<Language>" and "<Address >" and "<Name>" and "<PhoneNumber>"
    When user calls "/maps/api/place/add/json" with http request
    Then The API request is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Examples:
      | Language     | Address            | Name            | PhoneNumber    |
      | "sad"        | "22 smart village" | "ahmed zakaria" | "21121650000"  |
      | "English-EN" | "23 smart village" | "ahmed saad"    | "211216500001" |
